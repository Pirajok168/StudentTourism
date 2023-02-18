package ru.shared.feature.filters.data.sql

import ru.commons.feature.search.data.database.DormitoriesTable
import ru.commons.feature.search.data.database.GetAllFilters

class FiltersDormitoriesDaoImpl(
    database: DormitoriesTable
): IFiltersDormitoriesDao {
    private val dbQuery = database.dormitoriesTableQueries
    override suspend fun getAllFilters(): List<GetAllFilters> {
        return dbQuery.getAllFilters().executeAsList()
    }
}