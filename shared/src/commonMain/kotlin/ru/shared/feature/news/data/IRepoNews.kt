package ru.shared.feature.news.data

import ru.shared.feature.news.data.model.NewsPresentation

interface IRepoNews {
    suspend fun getAllNews(): List<NewsPresentation>
}