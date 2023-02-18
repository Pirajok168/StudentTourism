package ru.shared.core.model.wrapper

import io.ktor.utils.io.errors.*
import kotlinx.coroutines.coroutineScope


suspend fun <T> provideCatch(call: suspend () -> T): ResponseRequest<T>  {
    return try {
        ResponseRequest.Success(call())
    } catch (e: IOException) {
        ResponseRequest.Error(error = ResponseError(message = e.message))
    }

}