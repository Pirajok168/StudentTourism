package ru.shared.feature.home.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.feature.home.api.IApiSearch
import ru.shared.feature.home.data.ktor.SearchImpl
import ru.shared.feature.home.data.sql.MostPopularDaoDormitoriesImpl
import ru.shared.feature.home.data.sql.IMostPopularDaoDormitories


internal val homeDormitoriesModule = DI.Module("SearchDormitoriesModule") {
    bind<IApiSearch>() with singleton {
        SearchImpl(instance())
    }

    bind<IMostPopularDaoDormitories>() with singleton {
        MostPopularDaoDormitoriesImpl(
            database = instance()
        )
    }


    bind<IRepositoryHome>() with singleton {
        RepositoryHomeImpl(instance(), instance(), instance(), instance(), instance(),instance())
    }
}