package ru.shared.core.ktor

import io.ktor.client.engine.*


expect class HttpEngineFactory constructor() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}