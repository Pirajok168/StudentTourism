package ru.shared.feature.dormitories.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.feature.dormitories.data.sql.DormitoriesDatabases
import ru.shared.feature.dormitories.data.sql.IDormitoriesDatabase

internal val dormitoriesModule = DI.Module("DormitoriesModule") {
    bind<IDormitoriesDatabase>() with singleton {
        DormitoriesDatabases(
            database = instance()
        )
    }
}