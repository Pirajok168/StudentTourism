package ru.shared.feature.profile.api.response


@kotlinx.serialization.Serializable
data class ProfileResponse(
    val WoS: String? = null,
    val birthday: String? = null,
    val createdTimestamp: Long,
    val departureCity: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val gender: String? = null,
    val id: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val name: String? = null,
    val phone: String? = null,
    val starredDormitories: List<String> = emptyList(),
    val studentRoleType: String? = null,
    val timestamp: Long? = null,
    val universityName: String? = null,
    val updatedTimestamp: Long? = null,
    val userRole: String? = null,
    val username: String? = null
)