package ru.shared.feature.auth.data

import io.ktor.client.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.firstOrNull
import ru.shared.core.datastore.IAuthSettingsRepository
import ru.shared.core.model.response.Token
import ru.shared.core.model.wrapper.ResponseError
import ru.shared.core.model.wrapper.ResponseRequest
import ru.shared.feature.auth.api.AuthApi
import ru.shared.feature.auth.api.requst.BodyRequest
import ru.shared.feature.auth.api.response.UserAuthResponse
import ru.shared.feature.auth.data.model.TypeUser


class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val preferences: IAuthSettingsRepository,
    private val api: AuthApi
) : IAuthRepository {

    override suspend fun auth(login: String, password: String): ResponseRequest<Token> {
        val a = provide {
            api.auth(
                bodyRequest = BodyRequest(
                    email = login,
                    password = password
                )
            )
        }
        return when(a){
            is ResponseRequest.Error -> ResponseRequest.Error(a.error)
            is ResponseRequest.Success -> {
                preferences.save(login, password, a.data)
                ResponseRequest.Success(a.data)
            }
        }



    }

    override suspend fun refreshToken(): ResponseRequest<Token> {
        val token = preferences.passwordLoginPar.firstOrNull() ?: return ResponseRequest.Error(
            ResponseError("non authorize")
        )
        return auth(token.email, token.password)
    }

    override suspend fun typeUser(): TypeUser {
        return api.typeUser().toPresent()
    }
}

private fun UserAuthResponse.toPresent(): TypeUser {
    return TypeUser(
        userRole.orEmpty()
    )
}


suspend fun <T> provide(call: suspend () -> T): ResponseRequest<T> {
    return try {
        ResponseRequest.Success(call())
    } catch (e: IOException) {
        ResponseRequest.Error(error = ResponseError(message = e.message))
    }

}