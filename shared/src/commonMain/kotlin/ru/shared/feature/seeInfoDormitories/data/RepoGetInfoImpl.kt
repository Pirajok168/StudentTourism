package ru.shared.feature.seeInfoDormitories.data

import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories
import ru.shared.feature.seeInfoDormitories.data.model.PresentationRoomsDetail
import ru.shared.feature.seeInfoDormitories.data.sql.IDataInfo
import ru.shared.feature.seeInfoDormitories.data.sql.toPresent

class RepoGetInfoImpl(
    private val dao: IDataInfo
): IRepoGetInfo {
    override suspend fun getInfoById(id: String): PresentationDetailDormitories {
       return dao.getInfoById(id).toPresent()
    // return dao.getInfoById(id)
    }

    override suspend fun getRoomById(id: String): PresentationRoomsDetail {
        return dao.getRoomById(id).toPresent()
    }
}