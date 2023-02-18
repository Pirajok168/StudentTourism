package ru.shared.feature.profile.api.response


@kotlinx.serialization.Serializable
data class ProfileResponse(
    val createdTimestamp: Long,
    val email: String? = null,
    val id: String,
    val lastName: String? = null,
    val name: String? = null,
    val phone: String? = null,
    val starredDormitories: List<String> = listOf(),
    val studentRoleType: String? = null,
    val timestamp: Long? = null,
    val updatedTimestamp: Long? = null,
    val userRole: String? = null,
    val username: String? = null
)