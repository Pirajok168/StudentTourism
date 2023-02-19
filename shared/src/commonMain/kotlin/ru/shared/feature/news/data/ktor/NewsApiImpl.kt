package ru.shared.feature.news.data.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.shared.feature.news.api.INewsApi
import ru.shared.feature.news.api.response.NewsResponse

class NewsApiImpl(
    private val htpClient: HttpClient
): INewsApi {
    override suspend fun getAllNews(): List<NewsResponse> {
        return htpClient.get("articles").body()
    }
}