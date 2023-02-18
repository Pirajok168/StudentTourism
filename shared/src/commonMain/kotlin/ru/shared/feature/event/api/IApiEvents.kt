package ru.shared.feature.event.api

import ru.shared.feature.event.api.response.events.EventsResponse

interface IApiEvents {
    suspend fun getAllEvents(): List<EventsResponse>
}