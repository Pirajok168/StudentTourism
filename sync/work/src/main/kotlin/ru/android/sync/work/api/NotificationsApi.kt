package ru.android.sync.work.api
import retrofit2.http.GET
import retrofit2.http.Header

interface NotificationsApi {
    companion object{
        const val BASE_URL = "https://stud-api.sabir.pro"
    }


    @GET("/notifications")
    suspend fun getNotifications(
        @Header("Authorization") token: String
    )
}