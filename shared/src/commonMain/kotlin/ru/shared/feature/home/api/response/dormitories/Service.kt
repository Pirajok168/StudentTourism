package ru.shared.feature.home.api.response.dormitories


@kotlinx.serialization.Serializable
data class ServiceResponseDormitories(
    val isFree: Boolean? = null,
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: String? = null
)