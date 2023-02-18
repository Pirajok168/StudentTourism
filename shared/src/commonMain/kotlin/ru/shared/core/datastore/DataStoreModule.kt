package ru.shared.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import okio.Path
import okio.Path.Companion.toPath
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton



private lateinit var dataStore: DataStore<Preferences>

private val lock = SynchronizedObject()
fun getDataStore(producePath: () -> String): DataStore<Preferences> =
    synchronized(lock) {
        if (::dataStore.isInitialized) {
            dataStore
        } else {
            PreferenceDataStoreFactory.createWithPath(produceFile = { producePath().toPath() })
                .also { dataStore = it }
        }
    }

internal val dataStoreModule = DI.Module("DataStoreModule") {



    bind<IAuthSettingsRepository>() with singleton {
        AuthSettingsRepository(
            dataStore = getDataStore{
                return@getDataStore fileSystem(
                    platformConfiguration = instance()
                ).path
            }
        )
    }
}