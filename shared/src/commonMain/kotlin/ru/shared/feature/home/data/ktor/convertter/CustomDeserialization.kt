package ru.shared.feature.home.data.ktor.convertter

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import ru.shared.feature.home.api.response.dormitories.DetailsResponseDormitories
import ru.shared.feature.home.api.response.dormitories.ResponseDormitories
import ru.shared.feature.home.api.response.dormitories.RoomResponse

object CustomDeserialization: KSerializer<List<ResponseDormitories>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ResponseDormitories"){
        element<String>("userId")
        element<String>("universityId")
        element<Long>("createdTimestamp")
        element<DetailsResponseDormitories>("details")
        element<Boolean>("onModeration")
        element<String>("id")
        element<Long>("timestamp")
        element<Long>("updatedTimestamp")
        element<JsonArray>("rooms")
    }

    override fun deserialize(decoder: Decoder): List<ResponseDormitories> {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement().jsonArray


        val responseDormitories = mutableListOf<ResponseDormitories>()
        element.forEach {
                elem: JsonElement ->
            if (elem.jsonObject.get("onModeration")?.jsonPrimitive?.content?.toBoolean() == false) {

                val js = buildJsonObject {
                    elem.jsonObject.forEach {
                        if (it.key != "rooms") put(it.key, it.value)
                    }
                }

                val preparedElement = decoder.json.decodeFromJsonElement<ResponseDormitories>(js)


                val crashedRoom: JsonElement? = elem.jsonObject["rooms"]

                val preparedRooms = mutableListOf<RoomResponse>()
                crashedRoom?.jsonObject?.forEach {
                    preparedRooms.add(decoder.json.decodeFromJsonElement<RoomResponse>(it.value))
                }

                responseDormitories.add(preparedElement.copy(rooms = preparedRooms.toList()))
            }
        }



        return responseDormitories.toList()
    }

    override fun serialize(encoder: Encoder, value: List<ResponseDormitories>) {
        error("Serialization is not supported")
    }

}