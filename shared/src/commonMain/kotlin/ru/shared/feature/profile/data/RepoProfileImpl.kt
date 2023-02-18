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

        val remote = daoProfile.getProfile("")?.toPresent()
        if (remote!=null){
            emit( FlowResponse.Success(remote) )
        }


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

    override suspend fun getProfileIntoBaseData(): PresentationProfile? {
        return daoProfile.getProfile("")?.toPresent()
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
        WoS = WoS,
        birthday = birthday,
        departureCity = departureCity,
        firstName = firstName,
        gender = gender,
        middleName = middleName,
        universityName = universityName,
    )
}

private fun ProfileResponse.toCache(): Profile {
    return Profile(
        email = email.orEmpty(),
        id = id.orEmpty(),
        lastName = lastName.orEmpty(),
        name = name.orEmpty(),
        phone = phone.orEmpty(),
        studentRoleType = studentRoleType.orEmpty(),
        userRole = userRole.orEmpty(),
        username = username.orEmpty(),
        universityName = universityName.orEmpty(),
        middleName = middleName.orEmpty(),
        gender = gender.orEmpty(),
        departureCity = departureCity.orEmpty(),
        birthday = birthday.orEmpty(),
        WoS = WoS.orEmpty(),
        firstName =firstName.orEmpty()
    )
}

