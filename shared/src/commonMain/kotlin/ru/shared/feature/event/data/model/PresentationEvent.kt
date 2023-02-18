package ru.shared.feature.event.data.model

data class PresentationEvent(
    val id: String,
    val idUniversity: String,
    val name: String,
    val price: String,
    val description: String,
    val photos: String,
    val type: TypeEvent,
    val wos: String,
    val fromDate: Long,
    val toDate: Long,
)