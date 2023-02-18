package ru.shared.feature.seeInfoDormitories.data.model

data class PresentationDetailDormitories(
    val regionUniversity: String,
    val nameUniversity: String,
    val idDormitories: String,
    val name: String,
    val city: String,
    val street: String,
    val houseNumber: String,
    val mealPlan: String,
    val minDays: String,
    val maxDays: String,
    val photos: List<String>,
    val requiredUniversityDocuments: String,
    val requiredStudentsDocuments: String,
    val lat: Double?,
    val lng: Double?,
    val phone: String,
    val email: String,
    val nameCommittee: String,
    val rooms: List<PresentationRoomsDetail>,
    val services: List<PresentationServices>,
    val reviews: List<ReviewsDetailPresentation>
)