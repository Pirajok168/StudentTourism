package ru.shared.feature.home.api.response.university



@kotlinx.serialization.Serializable
data class DetailsUniversityResponse(
    val adminContacts: String? = null,
    val city: String? = null,
    val committee: String? = null,
    val district: String? = null,
    val founderName: String? = null,
    val name: String? = null,
    val photo: String? = null,
    val region: String? = null,
    val shortName: String? = null,
    val site: String? = null
)