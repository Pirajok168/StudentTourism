package ru.android.notification.datasource.sql

import ru.android.notification.datasource.NotificationEntity
import ru.android.notification.datasource.NotificationTable

interface IDaoNotification {
    suspend fun getNotification(): List<NotificationEntity>
    suspend fun setNotification(notificationTable: List<NotificationEntity>)
}