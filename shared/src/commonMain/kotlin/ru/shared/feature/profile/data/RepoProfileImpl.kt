package ru.shared.feature.profile.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.commons.feature.profile.data.database.IDaoProfile
import ru.commons.feature.profile.data.database.Profile
import ru.commons.feature.profile.data.database.entity.ProfileEntity
import ru.shared.core.model.wrapper.FlowResponse
import ru.shared.core.model.wrapper.ResponseRequest
import ru.shared.core.model.wrapper.provideCatch
import ru.shared.feature.profile.api.IApiProfile
import ru.shared.feature.profile.api.response.ProfileResponse
import ru.shared.feature.profile.data.ktor.toPresent
import ru.shared.feature.profile.data.model.PresentationProfile

class RepoProfileImpl(
    private val api: IApiProfile,
    private val daoProfile: IDaoProfile
): IRepoProfile {
    override suspend fun getProfile(): Flow<FlowResponse<PresentationProfile>> = flow {
        emit(FlowResponse.Loading())

        emit( FlowResponse.Success(daoProfile.getProfile("").toPresent()) )

        when(val response = provideCatch {  api.getProfile() }){
            is ResponseRequest.Error -> {
                emit(FlowResponse.Error(response.error))
            }
            is ResponseRequest.Success -> {
                daoProfile.setProfile(response.data.toCache())
                emit( FlowResponse.Success(response.data.toPresent()) )
            }
        }



        emit(FlowResponse.Loading(false))
    }
}

private fun Profile.toPresent(): PresentationProfile {
    return PresentationProfile(
        email = email.orEmpty(),
        id = id.orEmpty(),
        lastName = lastName.orEmpty(),
        name = name.orEmpty(),
        phone = phone.orEmpty(),
        studentRoleType = studentRoleType.orEmpty(),
        userRole = userRole.orEmpty(),
        username = username.orEmpty(),
    )
}

private fun  ProfileResponse.toCache(): ProfileEntity {
    return ProfileEntity(
        createdTimestamp,
        email = email.orEmpty(),
        id = id.orEmpty(),
        lastName = lastName.orEmpty(),
        name = name.orEmpty(),
        phone = phone.orEmpty(),
        studentRoleType = studentRoleType.orEmpty(),
        userRole = userRole.orEmpty(),
        username = username.orEmpty(),
        starredDormitories = starredDormitories,
        timestamp = timestamp!!,
        updatedTimestamp = updatedTimestamp!!
    )
}

