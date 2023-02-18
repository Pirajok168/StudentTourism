package ru.shared.feature.event.api.response.events


@kotlinx.serialization.Serializable
data class DatesEventsResponse(
    val from: Long? = null,
    val to: Long? = null
)