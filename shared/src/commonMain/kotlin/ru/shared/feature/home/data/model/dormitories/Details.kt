package ru.shared.feature.home.data.model.dormitories


data class Details(
    val name: String,
    val city: String,
    val street: String,
    val houseNumber: String,
    val coordinates: Coordinates,
    val mealPlan: String,
    val minDays: String,
    val maxDays: String,
    val photos: List<String>,

    val requiredUniversityDocuments: String,
    val requiredStudentsDocuments: String,

    val committee: Committee
)