package ru.shared.feature.home.api.response.dormitories
@kotlinx.serialization.Serializable
data class MainInfoResponseDormitories(
    val name: String? = null,
    val city: String? = null,
    val street: String? = null,
    val houseNumber: String? = null,
    val coordinates: CoordinatesResponseDormitories? = null,
    val mealPlan: String? = null,
    val minDays: String? = null,
    val maxDays: String? = null,
    val photos: List<String>? = null

)