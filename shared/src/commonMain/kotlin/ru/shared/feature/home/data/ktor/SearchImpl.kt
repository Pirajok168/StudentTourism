package ru.shared.feature.home.data.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import kotlinx.serialization.json.*

import ru.shared.feature.home.api.IApiSearch
import ru.shared.feature.home.api.response.dormitories.ResponseDormitories
import ru.shared.feature.home.api.response.reviews.ReviewResponse
import ru.shared.feature.home.api.response.university.UniversityResponse
import ru.shared.feature.home.data.ktor.convertter.CustomDeserialization


class SearchImpl(
    private val httpClient: HttpClient
): IApiSearch {
    override suspend fun searchDormitoriesAll(): List<ResponseDormitories> {
       return Json {
           prettyPrint = true
           isLenient = true
           ignoreUnknownKeys = true
       }.decodeFromString(CustomDeserialization, httpClient.get("dormitories/all") {}.bodyAsText())

    }

    override suspend fun searchUniversitiesAll(): List<UniversityResponse> {
        //throw IOException("Ошибка")
        return httpClient.get("universities/all").body()
    }

    override suspend fun searchReviewsAll(): List<ReviewResponse> {
        return httpClient.get("reviews").body()
    }

}