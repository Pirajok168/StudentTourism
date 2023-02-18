package ru.shared.feature.auth.api.requst

@kotlinx.serialization.Serializable
data class BodyRequest(
    val email: String,
    val password: String
)
