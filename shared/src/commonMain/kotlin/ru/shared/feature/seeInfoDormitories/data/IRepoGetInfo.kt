package ru.shared.feature.seeInfoDormitories.data

import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories
import ru.shared.feature.seeInfoDormitories.data.model.PresentationRoomsDetail

interface IRepoGetInfo {
    suspend fun getInfoById(id: String): PresentationDetailDormitories

    suspend fun getRoomById(id:String): PresentationRoomsDetail
}