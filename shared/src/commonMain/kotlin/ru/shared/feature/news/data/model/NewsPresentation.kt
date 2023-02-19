package ru.shared.feature.news.data.model

data class NewsPresentation(
    val content: String,
    val cover: String,
    val createdTimestamp: Long,
    val id: String,
    val tags: List<String> = emptyList(),
    val timestamp: Long,
    val title: String,
)
