package ru.android.notification.datasource

import ru.android.notification.datasource.ktor.model.PresentationNotifications

interface IRepoNotifications {
    suspend fun getNotifications(): List<PresentationNotifications>
}