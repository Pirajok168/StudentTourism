package ru.shared.feature.labs.data.sql

import ru.commons.feature.search.data.database.DormitoriesTable
import ru.commons.feature.search.data.database.Lab

class LabDatabase(
    database: DormitoriesTable
) : ILabsDatabase {
    private val dbQuery = database.labsDatabaseQueries

    override suspend fun setLab(labs: List<Lab>) {
        labs.forEach {
            it.apply {
                dbQuery.setLab(
                    id,
                    universityId,
                    address,
                    city,
                    contactsName,
                    description,
                    email,
                    establishmentYear,
                    link,
                    name,
                    phone,
                    photos
                )
            }
        }
    }

}