package ru.shared.core.datastore

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import okio.Path.Companion.toPath
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal val dataStoreModule = DI.Module("DataStoreModule") {
    bind<IAuthSettingsRepository>() with singleton {
        AuthSettingsRepository(
            dataStore = PreferenceDataStoreFactory.createWithPath(produceFile = {
                fileSystem(
                    platformConfiguration = instance()
                ).path.toPath()
            })
        )
    }
}