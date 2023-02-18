package ru.commons.feature.profile.data.database

interface IDaoProfile {
    suspend fun getProfile(id: String): Profile?

    suspend fun setProfile(profileEntity: Profile)
}