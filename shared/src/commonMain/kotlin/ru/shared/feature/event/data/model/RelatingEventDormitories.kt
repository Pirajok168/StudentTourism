package ru.shared.feature.event.data.model



data class RelatingEventDormitories(
     val id: String,
     val idUniversity: String,
     val name: String,
     val link: String,
     val price: String,
     val description: String,
     val video: List<String>,
     val photos: List<String>,
     val type: TypeEvent,
     val wos: String,
)