package ru.shared.feature.userReccomendations.data.sql

import ru.commons.feature.userrecommendation.data.database.UserRecomendations
import ru.shared.feature.event.data.sql.toTypeEvent
import ru.shared.feature.userReccomendations.data.model.PresentUserRecommendations

fun UserRecomendations.toPresent(): PresentUserRecommendations = PresentUserRecommendations(
    destrict.orEmpty(), toTypeEvent(typeEvent.orEmpty())
)