package ru.shared.feature.home.api.response.dormitories

import kotlinx.serialization.SerialName



@kotlinx.serialization.Serializable
data class DetailsResponseDormitories(
    @SerialName("main-info") val mainInfo: MainInfoResponseDormitories? = null,
    val services: List<ServiceResponseDormitories>? = null,
    val rules: RulesResponseDormitories? = null,
    val documents: List<String>? = null,
)