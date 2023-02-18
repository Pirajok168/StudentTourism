package ru.shared.feature.auth.data.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.shared.core.model.response.Token
import ru.shared.feature.auth.api.AuthApi
import ru.shared.feature.auth.api.requst.BodyRequest
import ru.shared.feature.auth.api.response.UserAuthResponse


class AuthApiImpl(
    private val httpClient: HttpClient
): AuthApi {

    override suspend fun auth(bodyRequest: BodyRequest): Token {
       return httpClient.post{
           url {
               path("users/login")
               setBody(bodyRequest)
               contentType(ContentType.Application.Json)
           }
       }.body()
    }

    override suspend fun typeUser(): UserAuthResponse {
        return httpClient.get{
            url {
                path("me")
            }
        }.body()
    }

}