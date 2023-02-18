package ru.commons.feature.userrecommendation.data.database

import app.cash.sqldelight.db.SqlDriver
import model.PlatformConfiguration


interface IDatabaseFactory {
    val sqlDriver: SqlDriver
}

expect fun createDriver(platformConf: PlatformConfiguration): IDatabaseFactory


class UserRecommendationsImpl(
    database: RecommendationTable
): IUserRecommendations {

    private val dbQuery = database.recommedationTableQueries
    override suspend fun getUserRecommendations(idUser: String): UserRecomendations? {
         return dbQuery.getUserRecomendations().executeAsOneOrNull()
    }

    override suspend fun setUserRecommendations(
        destrict: List<String>,
        typeEvent: String
    ) {
        dbQuery.setUserRecomendations("", destrict, typeEvent)
    }
}