package ru.android.notification.datasource.ktor.model

data class PresentationNotifications(
    public val id: String,
    public val name: String,
    public val type: String,
    public val createdTimestamp: Long,
    public val userId: String,
    val text: String,
)