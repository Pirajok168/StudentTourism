package ru.commons.feature.search.data.database

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.PlatformConfiguration
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.commons.feature.search.data.database.entity.ServicesEntity





val dormitoriesDatabasesModule = DI.Module("DormitoriesDatabasesModule") {
    bind<DormitoriesTable>() with singleton {
        val sqlDriver = createDriver(instance()).sqlDriver
        val listAdapter = object : ColumnAdapter<List<ServicesEntity>, String> {
            override fun decode(databaseValue: String): List<ServicesEntity> {
                return Json { }.decodeFromString(databaseValue)
            }

            override fun encode(value: List<ServicesEntity>): String {
                return Json {}.encodeToString(value)
            }

        }
        val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {
            override fun decode(databaseValue: String) =
                if (databaseValue.isEmpty()) {
                    listOf()
                } else {
                    databaseValue.split(",")
                }

            override fun encode(value: List<String>) = value.joinToString(separator = ",")
        }

        DormitoriesTable(
            sqlDriver,
            DormitorieTableAdapter = DormitorieTable.Adapter(listAdapter),
            DetailsTableAdapter = DetailsTable.Adapter(listOfStringsAdapter),
            RoomsTableAdapter = RoomsTable.Adapter(listOfStringsAdapter),
            EventAdapter = Event.Adapter(listOfStringsAdapter, listOfStringsAdapter),
            LabAdapter = Lab.Adapter(listOfStringsAdapter)
        )
    }
}

