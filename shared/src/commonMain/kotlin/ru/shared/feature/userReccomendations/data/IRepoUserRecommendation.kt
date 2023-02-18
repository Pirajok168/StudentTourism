package ru.shared.feature.userReccomendations.data

import ru.shared.feature.userReccomendations.data.model.PresentUserRecommendations

interface IRepoUserRecommendation {
    suspend fun setUserRecommendations(destrict: List<String>, typeEvent: String)

    suspend fun getUserRecommendations(idUser: String = ""): PresentUserRecommendations?
}