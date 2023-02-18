package ru.shared.feature.home.api.response.dormitories


@kotlinx.serialization.Serializable
data class ResponseDormitories(
    val userId: String? = null,
    val universityId: String? = null,
    val createdTimestamp: Long? = null,
    val details: DetailsResponseDormitories? = null,
    val onModeration: Boolean? = null,
    val id: String? = null,
    val timestamp: Long? = null,
    val updatedTimestamp: Long? = null,

    val rooms: List<RoomResponse> = emptyList()
)


