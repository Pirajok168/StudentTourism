package ru.shared.feature.seeInfoDormitories.data.sql

import ru.commons.feature.search.data.database.DormitoriesTable
import ru.commons.feature.search.data.database.RoomsTable
import ru.shared.feature.seeInfoDormitories.data.sql.model.InfoEntity


class DataInfoDatabase(
    database: DormitoriesTable
): IDataInfo {
    val dbQuery = database.infoTableQueries
    override suspend fun getInfoById(id: String): InfoEntity {
        val dormi = dbQuery.getDormitorieById(id).executeAsOne()
        val room = dbQuery.getRoomsById(id).executeAsList()
        val reviews = dbQuery.getReviewsById(id).executeAsList()
        return InfoEntity(dormi, room, reviews)
    }

    override suspend fun getRoomById(id: String): RoomsTable {
        return dbQuery.getRoomsByHerId(id).executeAsOne()
    }
}