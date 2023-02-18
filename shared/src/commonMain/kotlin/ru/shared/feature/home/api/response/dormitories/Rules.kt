package ru.shared.feature.home.api.response.dormitories

@kotlinx.serialization.Serializable
data class RulesResponseDormitories(
    val requiredUniDocuments: String? = null,
    val requiredStudentsDocuments: String? = null,
    val committee: CommitteeResponseDormitories? = null
)