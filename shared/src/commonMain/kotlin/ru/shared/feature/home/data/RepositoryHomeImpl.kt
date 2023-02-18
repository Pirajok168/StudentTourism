package ru.shared.feature.home.data

import com.soywiz.klock.DateTime
import kotlinx.coroutines.flow.*
import ru.commons.feature.search.data.database.GetAllDormitories
import ru.shared.core.model.wrapper.FlowResponse
import ru.shared.core.model.wrapper.ResponseError
import ru.shared.core.model.wrapper.ResponseRequest
import ru.shared.core.model.wrapper.provideCatch
import ru.shared.feature.dormitories.data.sql.IDormitoriesDatabase
import ru.shared.feature.event.api.IApiEvents
import ru.shared.feature.event.data.sql.IEventsDatabase
import ru.shared.feature.event.data.sql.toCache
import ru.shared.feature.home.api.IApiSearch
import ru.shared.feature.home.data.model.MostPopular
import ru.shared.feature.home.data.model.RecommendedDormitories
import ru.shared.feature.home.data.sql.IMostPopularDaoDormitories
import ru.shared.feature.home.data.sql.toCache
import ru.shared.feature.home.data.sql.toPreview
import ru.shared.feature.userReccomendations.data.IRepoUserRecommendation

class RepositoryHomeImpl(
    private val api: IApiSearch,
    private val apiEvent: IApiEvents,
    private val daoDormitories: IDormitoriesDatabase,
    private val mostPopularDaoDormitories: IMostPopularDaoDormitories,
    private val eventDao: IEventsDatabase,
    private val repoUserRecommendation: IRepoUserRecommendation
) : IRepositoryHome {


    override suspend fun getUserRecommendations(): ResponseRequest<List<RecommendedDormitories>> {
        val userRecommendation = repoUserRecommendation.getUserRecommendations() ?: return ResponseRequest.Error(ResponseError(message = "Не заданы рекомендации"))
        return ResponseRequest.Success(daoDormitories.getAllDormitories().filter { it.idDormitories != null && userRecommendation.district.contains(it.districtUniversity)}.map { it.toPresent() })
    }

    override suspend fun getMostPopular(): Flow<FlowResponse<List<MostPopular>>> =

        flow<FlowResponse<List<MostPopular>>> {
            emit(FlowResponse.Loading())
            val cashedData = mostPopularDaoDormitories.getMostPopular().map { it.toPreview() }
            if (cashedData.isNotEmpty())
                emit(FlowResponse.Success(cashedData))

            val responseUniversitiesAll = provideCatch {
                api.searchUniversitiesAll()
            }.data
                ?: return@flow emit(FlowResponse.Error(error = ResponseError(message = "Сервер не отвечает 1")))


            val responseSearchDormitories = provideCatch {
                api.searchDormitoriesAll()
            }.data
                ?: return@flow emit(FlowResponse.Error(error = ResponseError(message = "Сервер не отвечает 2")))


            val responseReviews = provideCatch {
                api.searchReviewsAll()
            }.data
                ?: return@flow emit(FlowResponse.Error(error = ResponseError(message = "Сервер не отвечает 3")))


            responseUniversitiesAll.filter { it.onModeration == true }.forEach {

                kotlin.runCatching {
                    it.toCache(responseSearchDormitories, responseReviews)
                }.onSuccess {
                    daoDormitories.setRelatedData(it)
                }

            }


            val data: List<MostPopular> = mostPopularDaoDormitories.getMostPopular().map { it.toPreview() }
            emit(FlowResponse.Success(data))
            loadEvents()
            emit(FlowResponse.Loading(false))
        }




    override suspend fun loadEvents() {
        val response = provideCatch { apiEvent.getAllEvents() }
        val nowDate = DateTime.now().unixMillisDouble
        eventDao.setEvent(response.data?.filter { it.details?.dates?.to!! > nowDate }
            ?.map { it.toCache() }.orEmpty())
    }



}

private fun GetAllDormitories.toPresent(): RecommendedDormitories {
    return RecommendedDormitories(
        districtUniversity,
        nameUniversity,
        idDormitories!!,
        nameUniversity,
        city,
        photos?.firstOrNull().orEmpty()
    )
}

