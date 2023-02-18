package ru.shared.feature.dormitories.data.sql

import ru.commons.feature.search.data.database.DetailsUniversityTable
import ru.commons.feature.search.data.database.GetAllDormitories
import ru.commons.feature.search.data.database.GetAllFilters
import ru.commons.feature.search.data.database.GetMostPopular
import ru.commons.feature.search.data.database.entity.DormitoriesEntity2
import ru.commons.feature.search.data.database.entity.RelationUniversityEntity


interface IDormitoriesDatabase {
    suspend fun setUniversity(universityTable: DetailsUniversityTable)
    suspend fun setAllInfoDormitories(
        dormitoriesTable: List<DormitoriesEntity2>,
    )
    suspend fun setRelatedData(dormitoriesEntity: RelationUniversityEntity)
    suspend fun getAllDormitories(): List<GetAllDormitories>
}
