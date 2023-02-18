package ru.android.notification.datasource.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.android.notification.api.response.IApiNotifications
import ru.android.notification.api.response.NotificationsResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiNotificationsImpl @Inject constructor (
    private val httpClient: HttpClient
): IApiNotifications {
    override suspend fun getNotifications(): List<NotificationsResponse> {
        return httpClient.get("notifications").body()
    }
}