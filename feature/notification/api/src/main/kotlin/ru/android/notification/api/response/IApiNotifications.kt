package ru.android.notification.api.response



interface IApiNotifications {
    suspend fun getNotifications():List<NotificationsResponse>
}