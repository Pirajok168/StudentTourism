package ru.shared.feature.booking.data.sql

import ru.commons.feature.search.data.database.DormitoriesTable
import ru.commons.feature.search.data.database.GetDormitorieById
import ru.shared.feature.seeInfoDormitories.data.sql.model.InfoEntity

class BookingDormitoriesImpl(
    database: DormitoriesTable
):IBookingDormitories {
    val dbQuery = database.infoTableQueries
    override suspend fun getInfoById(id: String): InfoEntity {
        val dormi = dbQuery.getDormitorieById(id).executeAsOne()
        val room = dbQuery.getRoomsById(id).executeAsList()
        val reviews = dbQuery.getReviewsById(id).executeAsList()
        return InfoEntity(dormi, room, reviews)
    }
}