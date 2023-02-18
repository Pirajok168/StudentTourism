package ru.commons.feature.userrecommendation.data.database

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
            RecommendationTable.Schema,
            context,
            "recommendationTable.db",
            callback = object : AndroidSqliteDriver.Callback(RecommendationTable.Schema) {
                override fun onConfigure(db: SupportSQLiteDatabase) {
                    super.onConfigure(db)
                    db.setForeignKeyConstraintsEnabled(true)
                }
            })

}


actual fun createDriver(platformConf: PlatformConfiguration): IDatabaseFactory = DatabaseDriverFactory(platformConf.androidContext)
