package ru.shared.feature.home.api.response.dormitories

import kotlinx.serialization.Serializable

@Serializable
data class DetailRoomResponse(
    val amount: String? = null,
    val description: String? = null,
    val isFree: Boolean? = null,
    val photos: List<String>? = null,
    val price: String? = null,
    val type: String? = null
)