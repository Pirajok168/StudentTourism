package ru.shared.feature.profile.data

import kotlinx.coroutines.flow.Flow
import ru.shared.core.model.wrapper.FlowResponse
import ru.shared.core.model.wrapper.ResponseRequest
import ru.shared.feature.profile.data.model.PresentationProfile

interface IRepoProfile {
    suspend fun getProfile(): Flow<FlowResponse<PresentationProfile>>

    suspend fun getProfileIntoBaseData(): PresentationProfile?
}