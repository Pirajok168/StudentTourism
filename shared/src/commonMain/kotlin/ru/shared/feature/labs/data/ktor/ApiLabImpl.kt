package ru.shared.feature.labs.data.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.shared.feature.labs.api.IApiLab
import ru.shared.feature.labs.api.reponse.LabResponse
import kotlin.collections.get

class ApiLabImpl(
    private val httpClient: HttpClient
): IApiLab {
    override suspend fun getAllEvents(): List<LabResponse> {
        return httpClient.get("labs/all"){}.body()
    }
}