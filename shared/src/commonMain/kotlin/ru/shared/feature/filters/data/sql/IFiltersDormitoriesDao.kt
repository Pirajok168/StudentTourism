package ru.shared.feature.filters.data.sql

import ru.commons.feature.search.data.database.GetAllFilters
import ru.commons.feature.search.data.database.GetMostPopular

interface IFiltersDormitoriesDao {
    suspend fun  getAllFilters(): List<GetAllFilters>
}