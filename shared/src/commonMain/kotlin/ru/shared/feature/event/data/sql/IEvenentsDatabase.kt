package ru.shared.feature.event.data.sql

import ru.commons.feature.search.data.database.Event
import ru.commons.feature.search.data.database.GetDormitoriesByIdUni
import ru.commons.feature.search.data.database.GetEventAndUniById

interface IEventsDatabase {
    suspend fun setEvent(event: List<Event>)

    suspend fun getEventByIdDormitories(id: String): List<Event>

    suspend fun getAllEvent(): List<Event>

    suspend fun getEventAndUniById(idUni: String, idEvent: String): GetEventAndUniById

    suspend fun getDormitoriesByIdUni(idUni: String): List<GetDormitoriesByIdUni>
}