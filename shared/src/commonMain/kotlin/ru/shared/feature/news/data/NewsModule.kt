package ru.shared.feature.news.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.feature.news.api.INewsApi
import ru.shared.feature.news.data.ktor.NewsApiImpl

internal val newsModule = DI.Module("NewsModule"){
    bind<INewsApi>() with singleton {
        NewsApiImpl(
            instance()
        )
    }

    bind<IRepoNews>() with singleton {
        RepoNewsImpl(
            instance()
        )
    }
}