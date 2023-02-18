package ru.commons.feature.search.data.database

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
            DormitoriesTable.Schema,
            context,
            "dormitoriesDatabase.db",
            callback = object : AndroidSqliteDriver.Callback(DormitoriesTable.Schema) {
                override fun onConfigure(db: SupportSQLiteDatabase) {
                    super.onConfigure(db)
                    db.setForeignKeyConstraintsEnabled(true)
                }
            })

}


actual fun createDriver(platformConf: PlatformConfiguration): IDatabaseFactory = DatabaseDriverFactory(platformConf.androidContext)