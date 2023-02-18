package ru.android.notification.datasource.sql

import app.cash.sqldelight.db.SqlDriver
import ru.android.notification.datasource.NotificationEntity
import ru.android.notification.datasource.NotificationTable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DaoNotificationsImpl @Inject constructor(
   val database: NotificationTable
): IDaoNotification {

    private val dbQueries = database.notificationTableQueries
    override suspend fun getNotification(): List<NotificationEntity> {
        return dbQueries.getNotificaions().executeAsList()
    }

    override suspend fun setNotification(notificationTable: List<NotificationEntity>) {
        notificationTable.forEach {
            it.apply {
                dbQueries.setNotifications(id, name, type, createdTimestamp, userId, text)
            }
        }
    }
}