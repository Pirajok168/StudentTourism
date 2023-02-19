package ru.shared.feature.news.data

import ru.shared.feature.news.api.INewsApi
import ru.shared.feature.news.api.response.NewsResponse
import ru.shared.feature.news.data.model.NewsPresentation

class RepoNewsImpl(
    private val api: INewsApi
): IRepoNews {
    override suspend fun getAllNews(): List<NewsPresentation> {
        return api.getAllNews().map { it.toPresentation() }
    }
}

private fun NewsResponse.toPresentation(): NewsPresentation {
    return NewsPresentation(
        content.orEmpty(),
        cover.orEmpty(),
        createdTimestamp!!,
        id.orEmpty(),
        tags.orEmpty(),
        timestamp!!,
        title.orEmpty()
    )
}
