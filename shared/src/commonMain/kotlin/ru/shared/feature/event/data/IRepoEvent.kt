package ru.shared.feature.event.data

import ru.shared.feature.event.data.model.DetailEvent
import ru.shared.feature.event.data.model.PresentationEvent
import ru.shared.feature.event.data.model.RelatingEventDormitories
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories
import ru.shared.feature.seeInfoDormitories.data.model.PresentationRoomsDetail

interface IRepoEvent {
    suspend fun getEventByIdDormitories(id: String): List<RelatingEventDormitories>

    suspend fun getAllEvent(): List<PresentationEvent>


    suspend fun getEventAndUniById(idUni: String, idEvent: String): DetailEvent

    suspend fun getRoomsByIdUni(idUni: String): List<PresentationDetailDormitories>
}