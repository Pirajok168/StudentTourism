package ru.shared.feature.event.api.response.events

import ru.shared.feature.event.api.response.events.DatesEventsResponse

@kotlinx.serialization.Serializable
data class DetailsEventResponse(
    val WoS: String? = null,
    val dates: DatesEventsResponse? = null,
    val description: String? = null,
    val isFree: Boolean? = null,
    val link: String? = null,
    val name: String? = null,
    val photos: List<String>? = null,
    val price: String? = null,
    val type: String? = null,
    val video: List<String>? = null
)