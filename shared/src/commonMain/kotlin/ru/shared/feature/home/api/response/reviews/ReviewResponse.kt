package ru.shared.feature.home.api.response.reviews


@kotlinx.serialization.Serializable
data class ReviewResponse(
    val createdTimestamp: Long? = null,
    val dormitoryId: String? = null,
    val id: String? = null,
    val onModeration: Boolean? = null,
    val eventId: String? = null,
    val photos: List<String>? = null,
    val published: Boolean? = null,
    val rating: Int? = null,
    val text: String? = null,
    val timestamp: Long? = null,
    val topic: String? = null,
    val updatedTimestamp: Long? = null,
    val userId: String? = null
)