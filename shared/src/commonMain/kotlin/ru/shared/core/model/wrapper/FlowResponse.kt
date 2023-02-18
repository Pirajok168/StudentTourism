package ru.shared.core.model.wrapper

sealed class FlowResponse<T>(open val data: T?, open val error: ResponseError?) {
    class Success<T>(override val data: T): FlowResponse<T>(data, null)
    class Loading<T>(val isLoading: Boolean = true): FlowResponse<T>(null, null)
    class Error<T>(override val error: ResponseError): FlowResponse<T>(error = error, data = null)
}