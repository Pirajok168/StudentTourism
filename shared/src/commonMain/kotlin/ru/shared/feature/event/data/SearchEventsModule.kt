package ru.shared.feature.event.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.feature.event.api.IApiEvents
import ru.shared.feature.event.data.ktor.EventSearchImpl
import ru.shared.feature.event.data.sql.EventDatabase
import ru.shared.feature.event.data.sql.IEventsDatabase


internal val searchEventsModule = DI.Module("searchEventsModule") {


    bind<IEventsDatabase>() with singleton {
        EventDatabase(
            database = instance()
        )
    }

    bind<IApiEvents>() with singleton {
        EventSearchImpl(instance())
    }

    bind<IRepoEvent>() with singleton {
        RepoEvent(
            dao = instance()
        )
    }
}