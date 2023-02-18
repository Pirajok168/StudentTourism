package ru.commons.feature.profile.data.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import model.PlatformConfiguration

class DatabaseDriverFactory(
    private val context: Context
): IDatabaseFactory {
    override val sqlDriver: SqlDriver
        get() = AndroidSqliteDriver(
            ProfileTable.Schema,
            context,
            "profileTable.db",
            callback = object : AndroidSqliteDriver.Callback(ProfileTable.Schema) {
                override fun onConfigure(db: SupportSQLiteDatabase) {
                    super.onConfigure(db)
                    db.setForeignKeyConstraintsEnabled(true)
                }
            })

}


actual fun createDriverProfile(platformConf: PlatformConfiguration): IDatabaseFactory = DatabaseDriverFactory(platformConf.androidContext)
