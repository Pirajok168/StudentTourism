package ru.shared.feature.labs.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.feature.labs.api.IApiLab
import ru.shared.feature.labs.data.ktor.ApiLabImpl
import ru.shared.feature.labs.data.sql.ILabsDatabase
import ru.shared.feature.labs.data.sql.LabDatabase


internal val searchLabsModule = DI.Module("searchLabsModule") {


    bind<ILabsDatabase>() with singleton {
        LabDatabase(
            database = instance()
        )
    }

    bind<IApiLab>() with singleton {
        ApiLabImpl(instance())
    }


}