package ru.shared.feature.auth.api

import ru.shared.feature.auth.api.requst.BodyRequest
import ru.shared.core.model.response.Token
import ru.shared.feature.auth.api.response.UserAuthResponse


interface AuthApi {

   suspend fun auth(bodyRequest: BodyRequest): Token

   suspend fun typeUser():UserAuthResponse

}