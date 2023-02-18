package ru.shared.feature.home.data

import kotlinx.coroutines.flow.Flow
import ru.shared.core.model.wrapper.FlowResponse
import ru.shared.core.model.wrapper.ResponseRequest
import ru.shared.feature.home.data.model.MostPopular
import ru.shared.feature.home.data.model.RecommendedDormitories


interface IRepositoryHome {

    suspend fun getMostPopular(): Flow<FlowResponse<List<MostPopular>>>
    suspend fun loadEvents()

}