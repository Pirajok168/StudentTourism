package ru.shared.feature.seeInfoDormitories.data.model

data class PresentationRoomsDetail(
     val idRooms: String,
     val idDormitories: String,
     val amountRooms: String,
     val descriptionRooms: String,
     val isFreeRooms: Long,
     val photosRooms: List<String>,
     val priceRooms: String,
     val typeRooms: String,
)