package ru.shared.feature.home.api.response.dormitories


@kotlinx.serialization.Serializable
data class CoordinatesResponseDormitories(
    val lat: Double? = null,
    val lng: Double? = null
)