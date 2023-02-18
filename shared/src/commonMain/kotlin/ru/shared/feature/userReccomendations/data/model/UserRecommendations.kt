package ru.shared.feature.userReccomendations.data.model

import ru.shared.feature.event.data.model.TypeEvent

data class PresentUserRecommendations(
    val district: List<String>,
    val typeEvent: TypeEvent
)
