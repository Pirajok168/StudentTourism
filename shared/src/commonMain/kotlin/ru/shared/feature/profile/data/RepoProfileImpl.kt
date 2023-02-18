package ru.shared.feature.profile.data

import ru.commons.feature.profile.data.database.DaoProfile
import ru.commons.feature.profile.data.database.IDaoProfile
import ru.commons.feature.profile.data.database.Profile
import ru.commons.feature.profile.data.database.entity.ProfileEntity
import ru.shared.feature.profile.api.IApiProfile
import ru.shared.feature.profile.api.response.ProfileResponse
import ru.shared.feature.profile.data.ktor.ApiProfileImpl
import ru.shared.feature.profile.data.ktor.toPresent
import ru.shared.feature.profile.data.model.PresentationProfile

class RepoProfileImpl(
    private val api: IApiProfile,
    private val daoProfile: IDaoProfile
): IRepoProfile {
    override suspend fun getProfile(): PresentationProfile {
        //val response =  api.getProfile()
        //daoProfile.setProfile(response.toCache())
        return daoProfile.getProfile("kQGFnUBFx5").toPresent()
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

