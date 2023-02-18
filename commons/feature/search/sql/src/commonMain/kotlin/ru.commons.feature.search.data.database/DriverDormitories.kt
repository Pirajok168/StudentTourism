package ru.commons.feature.search.data.database

import app.cash.sqldelight.db.SqlDriver
import model.PlatformConfiguration

interface IDatabaseFactory {
    val sqlDriver: SqlDriver
}

expect fun createDriver(platformConf: PlatformConfiguration): IDatabaseFactory