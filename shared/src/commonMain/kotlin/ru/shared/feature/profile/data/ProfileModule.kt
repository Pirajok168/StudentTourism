package ru.shared.feature.profile.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.commons.feature.profile.data.database.DaoProfile
import ru.commons.feature.profile.data.database.IDaoProfile
import ru.commons.feature.profile.data.database.ProfileTable
import ru.commons.feature.profile.data.database.createDriverProfile
import ru.shared.feature.profile.api.IApiProfile
import ru.shared.feature.profile.data.ktor.ApiProfileImpl

internal val profileModule = DI.Module("ProfileModule") {
    bind<IDaoProfile>() with singleton {
        DaoProfile(
            dataBase = ProfileTable(createDriverProfile(instance()).sqlDriver)
        )
    }

    bind<IApiProfile>() with singleton {
        ApiProfileImpl(
            instance()
        )
    }


    bind<IRepoProfile>() with singleton {
        RepoProfileImpl(
            api = instance(),
            daoProfile = instance()
        )
    }

}