package ru.shared.core.ktor

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorClient(
    private val engineFactory: HttpClientEngineFactory<HttpClientEngineConfig>
){
    private val token: String = ""
    private var client: HttpClient = HttpClient(engineFactory) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            /*install(HttpTimeout) {
                requestTimeoutMillis = 9000
            }*/

            HttpResponseValidator {
                validateResponse { response ->
                    val code: Int = response.status.value
                    if (code !in 200..299){

                    }
                }
            }

            defaultRequest {
                host = "stud-api.sabir.pro"
                headers.append("Authorization", token)
                url {
                    protocol = URLProtocol.HTTPS
                }

            }
        }
    }

    init {
        client
            .plugin(HttpSend)
            .intercept {
                    request ->
                val originalCall = execute(request)
                if (originalCall.response.status.value == 401) {
                    request.url {
                        path("universities/all")
                        //headersOf("Authorization","r:b93154d0d162f7dec327d10b1a5316b2")
                    }
                    request
                    execute(request)
                } else {
                    originalCall
                }
            }
    }
}