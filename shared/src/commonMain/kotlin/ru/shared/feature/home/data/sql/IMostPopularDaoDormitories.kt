package ru.shared.feature.home.data.sql

import ru.commons.feature.search.data.database.GetMostPopular


interface IMostPopularDaoDormitories {
    suspend fun getMostPopular(): List<GetMostPopular>
}
