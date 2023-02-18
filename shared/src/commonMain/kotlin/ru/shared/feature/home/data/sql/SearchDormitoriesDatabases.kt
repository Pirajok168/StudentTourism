package ru.shared.feature.home.data.sql

import ru.commons.feature.search.data.database.DormitoriesTable
import ru.commons.feature.search.data.database.GetMostPopular


class MostPopularDaoDormitoriesImpl(
    database: DormitoriesTable
) : IMostPopularDaoDormitories {

    private val dbQuery = database.dormitoriesTableQueries

    override suspend fun getMostPopular(): List<GetMostPopular> {
        return dbQuery.getMostPopular().executeAsList().groupBy {
            it.name
        }.map { (key, value) ->
            var _pre = value.reduce { a, b -> a.copy(rating = a.rating + b.rating) }

            _pre = _pre.copy(rating = _pre.rating / value.size)
            _pre
        }
    }


  /*  override suspend fun getRegionUniversity(): List<String> {
        return dbQuery.getRegionUniversity().executeAsList()
    }

    override suspend fun getShortNameUniversity(): List<String> {
        return dbQuery.getShortNameUniversity().executeAsList()
    }

    override suspend fun getAllFilters(): List<GetAllFilters> {
        //val test =  dbQuery.getAllFilters().executeAsList().groupBy { it.regionUniversity }


        return dbQuery.getAllFilters().executeAsList()
    }*/


}
