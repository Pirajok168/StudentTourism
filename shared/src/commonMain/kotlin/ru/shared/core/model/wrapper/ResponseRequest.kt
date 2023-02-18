package ru.shared.core.model.wrapper

sealed class ResponseRequest<T>(open val data: T?, open val error: ResponseError?) {

    data class Success<T>(override val data: T) : ResponseRequest<T>(data, null)
    data class Error<T>(override val error: ResponseError) : ResponseRequest<T>(null, error)
}