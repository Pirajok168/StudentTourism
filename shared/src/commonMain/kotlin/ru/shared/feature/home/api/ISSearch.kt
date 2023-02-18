package ru.shared.feature.home.api

import ru.shared.feature.home.api.response.dormitories.ResponseDormitories
import ru.shared.feature.home.api.response.reviews.ReviewResponse
import ru.shared.feature.home.api.response.university.UniversityResponse

interface IApiSearch {
    suspend fun searchDormitoriesAll(): List<ResponseDormitories>

    suspend fun searchUniversitiesAll(): List<UniversityResponse>

    suspend fun searchReviewsAll(): List<ReviewResponse>

}