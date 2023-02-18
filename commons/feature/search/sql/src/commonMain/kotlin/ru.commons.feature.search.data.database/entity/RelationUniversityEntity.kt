package ru.commons.feature.search.data.database.entity

import ru.commons.feature.search.data.database.DetailsTable
import ru.commons.feature.search.data.database.DetailsUniversityTable
import ru.commons.feature.search.data.database.ReviewDormitoriesTable
import ru.commons.feature.search.data.database.RoomsTable


data class DormitoriesEntity2(
    val idDormitories: String,
    val idUniversity: String,
    val details: DetailsTable,
    val rooms: List<RoomsTable>,
    val reviews: List<ReviewDormitoriesTable>,
    val services: List<ServicesEntity>
)


data class RelationUniversityEntity(
    val university: DetailsUniversityTable,
    val listDormitories: List<DormitoriesEntity2>,
)
