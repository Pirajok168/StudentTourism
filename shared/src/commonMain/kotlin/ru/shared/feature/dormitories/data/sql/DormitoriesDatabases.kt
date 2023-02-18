package ru.shared.feature.dormitories.data.sql

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
import ru.commons.feature.search.data.database.DetailsUniversityTable
import ru.commons.feature.search.data.database.DormitoriesTable
import ru.commons.feature.search.data.database.GetAllDormitories
import ru.commons.feature.search.data.database.entity.DormitoriesEntity2
import ru.commons.feature.search.data.database.entity.RelationUniversityEntity
import ru.commons.feature.search.data.database.entity.ServicesEntity



class DormitoriesDatabases(
    database: DormitoriesTable
) : IDormitoriesDatabase {

    private val dbQuery = database.dormitoriesTableQueries

    override suspend fun setUniversity(universityTable: DetailsUniversityTable) {
        universityTable.apply {
            dbQuery.setUniversity(
                idUniversity,
                adminContactsUniversity,
                cityUniversity,
                committeeUniversity,
                districtUniversity,
                founderNameUniversity,
                nameUniversity,
                photoUniversity,
                regionUniversity,
                shortNameUniversity,
                siteUniversity
            )
        }
    }

    override suspend fun setAllInfoDormitories(
        dormitoriesTable: List<DormitoriesEntity2>,
    ) {

        dormitoriesTable.forEach {
            it.apply {
                dbQuery.setAllDormitories(idDormitories, idUniversity, services)

                details.apply {
                    dbQuery.setAllDetailsDormitorie(
                        idDormitories,
                        name,
                        city,
                        street,
                        houseNumber,
                        mealPlan,
                        minDays,
                        maxDays,
                        photos,
                        requiredUniversityDocuments,
                        requiredStudentsDocuments,
                        lat,
                        lng,
                        phone,
                        email,
                        nameCommittee
                    )
                }
                rooms.forEach {
                    it.apply {
                        dbQuery.setAllRooms(
                            idRooms,
                            idDormitories,
                            amountRooms,
                            descriptionRooms,
                            isFreeRooms,
                            photosRooms,
                            priceRooms,
                            typeRooms
                        )
                    }
                }

                reviews.forEach {
                    it.apply {
                        dbQuery.setReview(
                            idReview,
                            createdTimestamp,
                            idDormitories,
                            photos,
                            rating,
                            text,
                            timestamp,
                            topic,
                            userId
                        )
                    }
                }
            }
        }
    }

    override suspend fun setRelatedData(dormitoriesEntity: RelationUniversityEntity) {
        dormitoriesEntity.apply {


            setUniversity(university)
            setAllInfoDormitories(
                listDormitories
            )
        }
    }

    override suspend fun getAllDormitories(): List<GetAllDormitories> {
        return dbQuery.getAllDormitories().executeAsList()
    }

}
