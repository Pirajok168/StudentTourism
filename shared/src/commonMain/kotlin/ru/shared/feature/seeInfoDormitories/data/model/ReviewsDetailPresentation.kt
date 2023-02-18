package ru.shared.feature.seeInfoDormitories.data.model

data class ReviewsDetailPresentation(
     val idReview: String,
     val createdTimestamp: Long,
     val idDormitories: String,
     val photos: String,
     val rating: Double,
     val text: String,
     val timestamp: Long,
     val topic: String,
     val userId: String,
)
