package ru.shared.feature.home.data.model


data class MostPopular(
    val image: String,
    val rating: Double,
    val title: String,
    val city: String,
    val isFavorite: Boolean = false,
    val idDormitories: String
)
