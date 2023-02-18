package ru.shared.feature.userReccomendations.data


import ru.commons.feature.userrecommendation.data.database.IUserRecommendations
import ru.shared.feature.userReccomendations.data.model.PresentUserRecommendations
import ru.shared.feature.userReccomendations.data.sql.toPresent

class RepoUserRecommendation(
    private val dao: IUserRecommendations,
): IRepoUserRecommendation {


    override suspend fun setUserRecommendations(
        destrict: List<String>,
        typeEvent: String
    ) {
        dao.setUserRecommendations(destrict, typeEvent)
    }

    override suspend fun getUserRecommendations(idUser: String): PresentUserRecommendations? {
       return dao.getUserRecommendations(idUser)?.toPresent()
    }
}