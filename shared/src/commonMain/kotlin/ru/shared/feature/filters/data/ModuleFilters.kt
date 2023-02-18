package ru.shared.feature.filters.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.feature.filters.data.sql.FiltersDormitoriesDaoImpl
import ru.shared.feature.filters.data.sql.IFiltersDormitoriesDao

internal val moduleFilters = DI.Module("ModuleFilters") {
    bind<IFiltersDormitoriesDao>() with singleton {
        FiltersDormitoriesDaoImpl(
            database = instance()
        )
    }


    bind<IRepoFilters>() with singleton {
        RepoFiltersImpl(
            dao = instance()
        )
    }
}