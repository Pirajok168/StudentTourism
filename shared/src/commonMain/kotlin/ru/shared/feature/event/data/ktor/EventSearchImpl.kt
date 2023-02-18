package ru.shared.feature.event.data.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

import ru.shared.feature.event.api.IApiEvents

import ru.shared.feature.event.api.response.events.EventsResponse


class EventSearchImpl(
    private val httpClient: HttpClient
): IApiEvents {
    override suspend fun getAllEvents(): List<EventsResponse> {
        return httpClient.get("events/all").body()
    }

}