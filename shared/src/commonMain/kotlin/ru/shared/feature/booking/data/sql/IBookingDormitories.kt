package ru.shared.feature.booking.data.sql

import ru.commons.feature.search.data.database.GetDormitorieById
import ru.shared.feature.seeInfoDormitories.data.sql.model.InfoEntity

interface IBookingDormitories {
    suspend fun getInfoById(id: String): InfoEntity
}