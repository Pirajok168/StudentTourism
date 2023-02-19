package ru.shared.feature.news.api.response

@kotlinx.serialization.Serializable
data class NewsResponse(
    val content: String? = null,
    val cover: String? = null,
    val createdTimestamp: Long? = null,
    val id: String? = null,
    val tags: List<String> = emptyList(),
    val timestamp: Long? = null,
    val title: String? = null,
    val updatedTimestamp: Long? = null,
    val userId: String? = null
)