package ru.shared.feature.labs.api

import ru.shared.feature.event.api.response.events.EventsResponse
import ru.shared.feature.labs.api.reponse.LabResponse

interface IApiLab {
    suspend fun getAllEvents(): List<LabResponse>
}