package ru.shared.feature.seeInfoDormitories.data.sql.model

import ru.commons.feature.search.data.database.GetDormitorieById
import ru.commons.feature.search.data.database.ReviewDormitoriesTable
import ru.commons.feature.search.data.database.RoomsTable

data class InfoEntity(
    val dormitories: GetDormitorieById,
    val rooms: List<RoomsTable>,
    val reviews: List<ReviewDormitoriesTable>
)