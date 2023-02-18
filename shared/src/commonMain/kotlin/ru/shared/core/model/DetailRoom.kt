package ru.shared.core.model

data class DetailRoom(
    val amount: String,
    val description: String,
    val isFree: Boolean,
    val photos: List<String>,
    val price: String,
    val type: String,
)
