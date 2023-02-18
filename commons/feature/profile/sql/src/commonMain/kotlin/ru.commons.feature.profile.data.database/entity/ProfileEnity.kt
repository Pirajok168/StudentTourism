package ru.commons.feature.profile.data.database.entity

data class ProfileEntity(
    val createdTimestamp: Long,
    val email: String,
    val id: String,
    val lastName: String,
    val name: String,
    val phone: String,
    val starredDormitories: List<String> = listOf(),
    val studentRoleType: String,
    val timestamp: Long,
    val updatedTimestamp: Long,
    val userRole: String,
    val username: String
)
