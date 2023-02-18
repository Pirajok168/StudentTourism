package ru.shared.feature.home.api.response.dormitories


@kotlinx.serialization.Serializable
data class RoomResponse(
    val createdTimestamp: Long ? = null,
    val details: DetailRoomResponse? = null,
    val dormitoryId: String? = null,
    val id: String? = null,
    val onModeration: Boolean? = null,
    val timestamp: Long? = null,
    val universityId: String? = null,
    val updatedTimestamp: Long? = null,
    val userId: String? = null
)