package ru.shared.feature.event.data

import ru.shared.feature.event.data.model.DetailEvent
import ru.shared.feature.event.data.model.PresentationEvent
import ru.shared.feature.event.data.model.RelatingEventDormitories
import ru.shared.feature.event.data.sql.IEventsDatabase
import ru.shared.feature.event.data.sql.toPresentation
import ru.shared.feature.event.data.sql.toRelatingPresent
import ru.shared.feature.event.data.sql.toView
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories

class RepoEvent(
    private val dao: IEventsDatabase
): IRepoEvent {
    override suspend fun getEventByIdDormitories(id: String): List<RelatingEventDormitories> {
       return dao.getEventByIdDormitories(id).map { it.toRelatingPresent() }
    }

    override suspend fun getAllEvent(): List<PresentationEvent> {
        return dao.getAllEvent().map { it.toPresentation() }
    }

    override suspend fun getEventAndUniById(idUni: String, idEvent: String): DetailEvent {
        return dao.getEventAndUniById(idUni, idEvent).toView()
    }

    override suspend fun getRoomsByIdUni(idUni: String): List<PresentationDetailDormitories> {
        return dao.getDormitoriesByIdUni(idUni).map { it.toView() }
    }
}