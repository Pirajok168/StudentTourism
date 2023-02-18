package ru.android.notification.api.response


@kotlinx.serialization.Serializable
data class NotificationsResponse(
    val createdTimestamp: Long? = null,
    val id: String? = null,
    val name: String? = null,
    val text: String? = null,
    val timestamp: Long? = null,
    val type: String? = null,
    val updatedTimestamp: Long? = null,
    val userId: String? = null
)