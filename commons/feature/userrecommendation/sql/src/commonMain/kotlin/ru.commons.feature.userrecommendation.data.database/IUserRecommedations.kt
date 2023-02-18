package ru.commons.feature.userrecommendation.data.database

interface IUserRecommendations {
    suspend fun getUserRecommendations(idUser: String): UserRecomendations?

    suspend fun setUserRecommendations(destrict:  List<String>, typeEvent: String)
}