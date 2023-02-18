package ru.shared.feature.seeInfoDormitories.data.sql

import ru.commons.feature.search.data.database.RoomsTable
import ru.shared.feature.seeInfoDormitories.data.sql.model.InfoEntity

interface IDataInfo {
    suspend fun getInfoById(id: String): InfoEntity

    suspend fun getRoomById(id: String): RoomsTable
}