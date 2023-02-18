package ru.shared.core.ktor


import androidx.datastore.core.DataStore
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.core.datastore.IAuthSettingsRepository

interface IMobileClient{
    val httpClient: HttpClient
}

class MobileClient(
   private val engine: HttpClientEngineFactory<HttpClientEngineConfig>,
   private val dataStore: IAuthSettingsRepository,
   private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
): IMobileClient{

    private var token: String?  = null

    init {
        coroutineScope.launch {
            dataStore.token.collect{
                token = it?.token
            }
        }
    }

    override val httpClient: HttpClient by lazy {
         HttpClient(engine){
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
             install(Logging) {
                 logger = Logger.DEFAULT
                 level = LogLevel.HEADERS
             }

            HttpResponseValidator {
                validateResponse { response ->
                    val code: Int = response.status.value
                    if (code == 403){
                        throw IOException("Неправильно написан логин или пароль")
                    }
                    if (code !in 200..226) {
                        throw IOException("Ошибка")
                    }
                }
            }
            defaultRequest {
                host = "stud-api.sabir.pro"

                headers.append("Authorization", token.orEmpty())
                url {
                    protocol = URLProtocol.HTTPS
                }

            }
        }
    }


}


internal val domainApi = DI.Module(
    name = "DomainApi",
    init = {
        bind<HttpEngineFactory>() with singleton { HttpEngineFactory() }

        bind<IMobileClient>() with singleton {
            MobileClient(instance<HttpEngineFactory>().createEngine(), instance())
        }

        bind<HttpClient>() with singleton {
            instance<IMobileClient>().httpClient
        }

       /* bind<HttpClient>() with singleton {
            val engine = instance<HttpEngineFactory>().createEngine()
            val preferences: IAuthSettingsRepository = instance()

            var token = ""
            val client = HttpClient(engine) {


                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }




                HttpResponseValidator {
                    validateResponse { response ->
                        val code: Int = response.status.value
                        if (code == 403){
                            throw IOException("Неправильно написан логин или пароль")
                        }
                        if (code !in 200..226) {
                            throw IOException("Ошибка")
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
            client
                .plugin(HttpSend)
                .intercept { request ->
                    val originalCall = execute(request)
                    if (originalCall.response.status.value == 401) {
                        //request.headers["Authorization"] = "r:b93154d0d162f7dec327d10b1a5316b2"

                        execute(request)
                    } else {
                        originalCall
                    }
                }

            client
        }*/
    }
)