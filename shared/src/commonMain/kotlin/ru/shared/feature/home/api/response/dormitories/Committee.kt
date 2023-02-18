package ru.shared.feature.home.api.response.dormitories


@kotlinx.serialization.Serializable
data class CommitteeResponseDormitories(
    val phone: String? = null,
    val email: String? = null,
    val name: String? = null
)