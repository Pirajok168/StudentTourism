package ru.shared.core.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val token: String
)