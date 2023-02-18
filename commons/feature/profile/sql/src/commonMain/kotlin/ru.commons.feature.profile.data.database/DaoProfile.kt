package ru.commons.feature.profile.data.database

import app.cash.sqldelight.db.SqlDriver
import model.PlatformConfiguration
import ru.commons.feature.profile.data.database.entity.ProfileEntity

interface IDatabaseFactory {
    val sqlDriver: SqlDriver
}

expect fun createDriverProfile(platformConf: PlatformConfiguration): IDatabaseFactory

class DaoProfile(
    dataBase: ProfileTable
): IDaoProfile {

    private val dbQuery = dataBase.profileTableQueries
    override suspend fun getProfile(id: String): Profile {
        return dbQuery.getUserProfile().executeAsOne()
    }

    override suspend fun setProfile(profileEntity: ProfileEntity) {
        profileEntity.apply {
            dbQuery.setProfile(
                id = id,
                username = username,
                email = email,
                userRole = userRole,
                name = name,
                phone = phone,
                studentRoleType = studentRoleType,
                lastName = lastName
            )
        }
    }
}