package ru.shared.feature.event.data.sql

import ru.commons.feature.search.data.database.Event
import ru.commons.feature.search.data.database.GetDormitoriesByIdUni
import ru.commons.feature.search.data.database.GetEventAndUniById
import ru.shared.core.model.DetailRoom
import ru.shared.feature.event.api.response.events.EventsResponse
import ru.shared.feature.event.data.model.DetailEvent
import ru.shared.feature.event.data.model.PresentationEvent
import ru.shared.feature.event.data.model.RelatingEventDormitories
import ru.shared.feature.event.data.model.TypeEvent
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories
import ru.shared.feature.seeInfoDormitories.data.model.PresentationRoomsDetail
import ru.shared.feature.seeInfoDormitories.data.model.PresentationServices
import ru.shared.feature.seeInfoDormitories.data.sql.toPresent
import ru.shared.feature.seeInfoDormitories.data.sql.toReviewsDetailPresentation

fun GetDormitoriesByIdUni.toView(): PresentationDetailDormitories = PresentationDetailDormitories(
    regionUniversity = "",
    nameUniversity = "",
    idDormitories = idDormitories.orEmpty(),
    name = name.orEmpty(),
    city = city.orEmpty(),
    street = street.orEmpty(),
    houseNumber = houseNumber.orEmpty(),
    mealPlan = mealPlan.orEmpty(),
    minDays = minDays.orEmpty(),
    maxDays = maxDays.orEmpty(),
    photos = photos.orEmpty(),
    requiredUniversityDocuments = requiredUniversityDocuments.orEmpty(),
    requiredStudentsDocuments = requiredStudentsDocuments.orEmpty(),
    lat = lat,
    lng = lng,
    phone = phone.orEmpty(),
    email = email.orEmpty(),
    nameCommittee = nameCommittee.orEmpty(),
    rooms = emptyList(),
    services = emptyList(),
    reviews = emptyList()
)

fun GetEventAndUniById.toView(): DetailEvent = DetailEvent(
    id,
    idUniversity,
    name,
    price,
    description,
    photos,
    video,
    toTypeEvent(type),
    wos,
    fromDate,
    toDate,
    shortNameUniversity.orEmpty(),
    cityUniversity.orEmpty()
)
fun Event.toPresentation(): PresentationEvent = PresentationEvent(
    id,
    idUniversity,
    name,
    price,
    description,
    photos.firstOrNull() ?: "",
    toTypeEvent(type),
    wos,
    fromDate,
    toDate
)

fun Event.toRelatingPresent(): RelatingEventDormitories = RelatingEventDormitories(
    id,
    idUniversity,
    name,
    link,
    price,
    description,
    video,
    photos,
    toTypeEvent(type),
    wos
)

fun toTypeEvent(type: String): TypeEvent{
    return when(type){
        "cultural" -> TypeEvent.Culture
        "scientific" -> TypeEvent.LabPopulation
        "proforientation" -> TypeEvent.Proforientation
        else ->{
            throw Exception("Тип не известен - $type")
        }
    }
}

fun EventsResponse.toCache(): Event = Event(
    id = id.orEmpty(),
    idUniversity = universityId.orEmpty(),
    name = details?.name.orEmpty(),
    link = details?.link.orEmpty(),
    price = details?.price.orEmpty(),
    description = details?.description.orEmpty(),
    video = details?.video.orEmpty(),
    photos = details?.photos.orEmpty(),
    type = details?.type.orEmpty(),
    wos = details?.type.orEmpty(),
    fromDate = details?.dates?.from!!,
    toDate = details.dates.to!!
)