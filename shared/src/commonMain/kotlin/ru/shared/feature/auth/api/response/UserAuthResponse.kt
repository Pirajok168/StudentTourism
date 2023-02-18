package ru.shared.feature.auth.api.response


@kotlinx.serialization.Serializable
data class UserAuthResponse(
    val createdTimestamp: Long? = null,
    val email: String? = null,
    val id: String? = null,
    val lastName: String? = null,
    val name: String? = null,
    val phone: String? = null,
    val starredDormitories: List<String>? = null,
    val studentRoleType: String? = null,
    val timestamp: Long? = null,
    val updatedTimestamp: Long? = null,
    val userRole: String? = null,
    val username: String? = null
)