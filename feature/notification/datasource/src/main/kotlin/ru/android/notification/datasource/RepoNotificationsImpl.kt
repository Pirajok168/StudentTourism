package ru.android.notification.datasource

import ru.android.notification.api.response.IApiNotifications
import ru.android.notification.api.response.NotificationsResponse
import ru.android.notification.datasource.ktor.model.PresentationNotifications
import ru.android.notification.datasource.sql.IDaoNotification
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoNotificationsImpl @Inject constructor (
    private val apiNotificationsImpl: IApiNotifications,
    private val daoNotificationsImpl: IDaoNotification,
) : IRepoNotifications {
    // TODO: Cохранять в базу данных 
    override suspend fun getNotifications():List<PresentationNotifications> {

        val dataRemote = daoNotificationsImpl.getNotification().map { it.toPresentation() }

        val newData = apiNotificationsImpl.getNotifications().map { it.toPresentation() }

        daoNotificationsImpl.setNotification(newData.map { it.toEntity() })
        val presentData = newData.subtract(dataRemote.toSet())

        return presentData.toList()
    }

}

private fun PresentationNotifications.toEntity(): NotificationEntity {
    return NotificationEntity(id, name, type, createdTimestamp, userId, text)
}

private fun NotificationsResponse.toPresentation(): PresentationNotifications {
    return PresentationNotifications(
        id.orEmpty(),
        name.orEmpty(),
        type.orEmpty(),
        createdTimestamp!!,
        userId.orEmpty(),
        text = text.orEmpty()
    )
}

private fun NotificationEntity.toPresentation(): PresentationNotifications {
    return PresentationNotifications(id, name, type, createdTimestamp, userId, text)
}

