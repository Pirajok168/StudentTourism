package ru.shared.feature.profile.api

import ru.shared.feature.profile.api.response.ProfileResponse

interface IApiProfile {
    suspend fun getProfile(): ProfileResponse
}