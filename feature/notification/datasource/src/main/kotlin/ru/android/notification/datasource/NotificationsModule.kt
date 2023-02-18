package ru.android.notification.datasource

import android.content.Context
import android.util.Log
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import ru.android.notification.api.response.IApiNotifications
import ru.android.notification.datasource.ktor.ApiNotificationsImpl
import ru.android.notification.datasource.sql.DaoNotificationsImpl
import ru.android.notification.datasource.sql.IDaoNotification
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {
    @Singleton
    @Provides
    fun provideNotificationApi(
      @ApplicationContext context: Context
    ): HttpClient = HttpClient(Android){
        val shared = context.getSharedPreferences("tokenautn", Context.MODE_PRIVATE)
        val token = shared.getString("token", "")
        Log.w("token", token.toString())
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            host = "stud-api.sabir.pro"
            headers.append("Authorization", token.orEmpty())
            url {
                protocol = URLProtocol.HTTPS
            }
        }
    }

    @Singleton
    @Provides
    fun provideSqlDriver(
        @ApplicationContext context: Context
    ): NotificationTable = NotificationTable(AndroidSqliteDriver(NotificationTable.Schema, context, "notificationTable.db"))
}


@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationRepoModule{
    @Singleton
    @Binds
    abstract fun bindApi(
        apiRepo: ApiNotificationsImpl
    ): IApiNotifications


    @Singleton
    @Binds
    abstract fun bindDao(
        dao: DaoNotificationsImpl
    ): IDaoNotification

    @Singleton
    @Binds
    abstract fun bindRepo(
        apiRepo: RepoNotificationsImpl
    ): IRepoNotifications

}
