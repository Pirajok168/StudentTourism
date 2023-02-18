package ru.shared.feature.labs.api.reponse


@kotlinx.serialization.Serializable
data class DetailsLabsResponse(
    val address: String? = null,
    val city: String? = null,
    val contactsName: String? = null,
    val description: String? = null,
    val email: String? = null,
    val establishmentYear: String? = null,
    val link: String? = null,
    val name: String? = null,
    val phone: String? = null,
    val photos: List<String>? = null
)