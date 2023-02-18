package ru.shared.feature.home.api.response.dormitories


@kotlinx.serialization.Serializable
data class ServicesResponse(
    val description: String? = null,
    val id: String? = null,
    val isFree: Boolean? = null,
    val name: String? = null,
    val price: String? = null
)