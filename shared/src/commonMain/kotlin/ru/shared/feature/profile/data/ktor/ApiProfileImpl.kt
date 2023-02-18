package ru.shared.feature.profile.data.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.shared.feature.profile.api.IApiProfile
import ru.shared.feature.profile.api.response.ProfileResponse

class ApiProfileImpl(
    private val httpClient: HttpClient
): IApiProfile {
    override suspend fun getProfile(): ProfileResponse {
        return httpClient.get("me").body()
    }
}