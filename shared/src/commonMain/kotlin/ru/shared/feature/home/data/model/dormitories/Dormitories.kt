package ru.shared.feature.home.data.model.dormitories

import ru.shared.feature.home.data.model.univiersity.University

data class Dormitories(
    val idDormitories: String,
    val details: Details,
    val rooms: List<Rooms>,
    val ownerUniversity: University,
    val reviews: List<ReviewDormitories>
)
