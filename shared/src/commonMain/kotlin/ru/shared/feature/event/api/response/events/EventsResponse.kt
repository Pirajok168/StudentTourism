package ru.shared.feature.event.api.response.events


@kotlinx.serialization.Serializable
data class EventsResponse(
    val createdTimestamp: Long? = null,
    val details: DetailsEventResponse? = null,
    val id: String? = null,
    val onModeration: Boolean? = null,
    val timestamp: Long? = null,
    val universityId: String? = null,
    val updatedTimestamp: Long? = null,
    val userId: String? = null
)