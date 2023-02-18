package ru.shared.feature.auth.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.feature.auth.api.AuthApi
import ru.shared.feature.auth.data.ktor.AuthApiImpl

internal val authModule = DI.Module("AuthModule") {
    bind<AuthApi>() with singleton {
        AuthApiImpl(
            httpClient = instance()
        )
    }

    bind<IAuthRepository>() with singleton {
        AuthRepositoryImpl(
            httpClient = instance(),
            preferences = instance(),
            api = instance()
        )
    }
}