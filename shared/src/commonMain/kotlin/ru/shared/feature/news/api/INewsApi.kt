package ru.shared.feature.news.api

import ru.shared.feature.news.api.response.NewsResponse


interface INewsApi {
    suspend fun getAllNews(): List<NewsResponse>
}