package ru.shared.feature.event.data.model

data class DetailEvent(
    val id: String,
    val idUniversity: String,
    val name: String,
    val price: String,
    val description: String,
    val photos: List<String>,
    val videos: List<String>,
    val type: TypeEvent,
    val wos: String,
    val fromDate: Long,
    val toDate: Long,
    val nameUnit: String,
    val city: String,
)