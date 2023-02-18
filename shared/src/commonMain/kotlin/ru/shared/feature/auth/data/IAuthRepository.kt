package ru.shared.feature.auth.data

import ru.shared.core.model.response.Token
import ru.shared.core.model.wrapper.ResponseRequest
import ru.shared.feature.auth.data.model.TypeUser


interface IAuthRepository {
    suspend fun auth(login: String, password: String): ResponseRequest<Token>
    suspend fun refreshToken(): ResponseRequest<Token>

    suspend fun typeUser(): TypeUser
}