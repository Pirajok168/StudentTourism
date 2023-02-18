package ru.commons.feature.profile.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import model.PlatformConfiguration


class DatabaseFactory: IDatabaseFactory {

    val dbConfig = DatabaseConfiguration(
        name = "profileTable.db",
        version = 1,
        create = { connection ->
            wrapConnection(connection) { ProfileTable.Schema.create(it) }
        },
        extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
    )
    override val sqlDriver: SqlDriver
        get() = NativeSqliteDriver(dbConfig)
}

actual fun createDriverProfile(platformConf: PlatformConfiguration): IDatabaseFactory = DatabaseFactory()