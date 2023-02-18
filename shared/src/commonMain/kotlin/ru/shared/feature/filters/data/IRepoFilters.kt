package ru.shared.feature.filters.data

import kotlinx.coroutines.flow.SharedFlow
import ru.commons.feature.search.data.database.GetAllFilters

interface IRepoFilters {
    val filteredData: SharedFlow<List<GetAllFilters>>

    suspend fun loadAllDataDormitories()

    suspend fun getAllFilters():List<GetAllFilters>
    suspend fun setFilteredData(list: List<GetAllFilters>)

    suspend fun searchByCity(input: String)
}