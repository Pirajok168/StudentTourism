package ru.shared.feature.home.data.model.dormitories

data class ReviewDormitories(
    val id: String,

    val createdTimestamp: Long,
    val dormitoryId: String,
    val photos: List<String>,
    val rating: Int,
    val text: String,
    val timestamp: Long,
    val topic: String,
    val userId: String
)
