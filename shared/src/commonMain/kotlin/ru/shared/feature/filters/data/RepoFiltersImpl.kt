package ru.shared.feature.filters.data

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.commons.feature.search.data.database.GetAllFilters
import ru.shared.feature.filters.data.sql.IFiltersDormitoriesDao

class RepoFiltersImpl(
    private val dao: IFiltersDormitoriesDao
): IRepoFilters {
    private val _filteredData = MutableSharedFlow<List<GetAllFilters>>(extraBufferCapacity = 2)
    override val filteredData: SharedFlow<List<GetAllFilters>>
        get() = _filteredData

    override suspend fun loadAllDataDormitories() {
        val data = dao.getAllFilters()
        _filteredData.emit(data.distinctBy { it.idDormitories })
    }

    override suspend fun getAllFilters(): List<GetAllFilters> {
        return dao.getAllFilters()
    }

    override suspend fun setFilteredData(list: List<GetAllFilters>) {
        _filteredData.emit(list)
    }

    override suspend fun searchByCity(input: String) {
        val a = dao.getAllFilters().filter { it.city!!.lowercase().contains(input.lowercase()) }
            .distinctBy { it.idDormitories }
        _filteredData.emit(a)
    }
}