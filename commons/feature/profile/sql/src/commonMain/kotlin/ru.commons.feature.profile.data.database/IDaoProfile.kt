package ru.commons.feature.profile.data.database

import ru.commons.feature.profile.data.database.entity.ProfileEntity

interface IDaoProfile {
    suspend fun getProfile(id: String): Profile?

    suspend fun setProfile(profileEntity: ProfileEntity)
}