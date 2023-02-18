package ru.shared.feature.home.api.response.university

@kotlinx.serialization.Serializable
data class UniversityResponse(
    val createdTimestamp: Long? = null,
    val details: DetailsUniversityResponse? = null,
    val id: String? = null,
    val onModeration: Boolean? = null,
    val timestamp: Long,
    val updatedTimestamp: Long? = null,
    val userId: String? = null
)