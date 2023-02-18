package ru.shared.feature.labs.api.reponse


@kotlinx.serialization.Serializable
data class LabResponse(
    val createdTimestamp: Long? = null,
    val details: DetailsLabsResponse? = null,
    val id: String? = null,
    val onModeration: Boolean? = null,
    val timestamp: Long? = null,
    val universityId: String? = null,
    val updatedTimestamp: Long? = null,
    val userId: String? = null
)