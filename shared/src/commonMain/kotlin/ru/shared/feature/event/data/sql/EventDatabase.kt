package ru.shared.feature.event.data.sql

import ru.commons.feature.search.data.database.DormitoriesTable
import ru.commons.feature.search.data.database.Event
import ru.commons.feature.search.data.database.GetDormitoriesByIdUni
import ru.commons.feature.search.data.database.GetEventAndUniById

class EventDatabase(
    database: DormitoriesTable
) : IEventsDatabase {
    private val dbQuery = database.eventDatabaseQueries
    override suspend fun setEvent(event: List<Event>) {
        event.forEach { event ->
            event.apply {
                dbQuery.setEvent(
                    id,
                    idUniversity,
                    name,
                    link,
                    price,
                    description,
                    video,
                    photos,
                    type,
                    wos,
                    fromDate,
                    toDate
                )
            }
        }


    }

    override suspend fun getEventByIdDormitories(id: String): List<Event> {
        return dbQuery.getAllEvent(id).executeAsList()
    }

    override suspend fun getAllEvent(): List<Event> {
        return dbQuery.getEvents().executeAsList()
    }

    override suspend fun getEventAndUniById(idUni: String, idEvent: String): GetEventAndUniById {
        return dbQuery.getEventAndUniById(idUni, idEvent).executeAsOne()
    }

    override suspend fun getDormitoriesByIdUni(idUni: String): List<GetDormitoriesByIdUni> {
        return dbQuery.getDormitoriesByIdUni(idUni).executeAsList()
    }

}