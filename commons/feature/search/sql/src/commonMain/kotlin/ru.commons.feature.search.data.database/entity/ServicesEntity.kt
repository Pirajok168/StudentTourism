package ru.commons.feature.search.data.database.entity


@kotlinx.serialization.Serializable
data class ServicesEntity(
    val description: String,
    val id: String,
    val isFree: Boolean,
    val name: String,
    val price: String
)