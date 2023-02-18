package ru.shared.feature.seeInfoDormitories.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.feature.seeInfoDormitories.data.sql.DataInfoDatabase
import ru.shared.feature.seeInfoDormitories.data.sql.IDataInfo

internal val infoModule = DI.Module("InfoModule") {
    bind<IDataInfo>() with singleton {
        DataInfoDatabase(
            database = instance()
        )
    }

    bind<IRepoGetInfo>() with singleton {
        RepoGetInfoImpl(
            dao = instance()
        )
    }

}