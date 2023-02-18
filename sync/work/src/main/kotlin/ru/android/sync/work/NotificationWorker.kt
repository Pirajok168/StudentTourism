package ru.android.sync.work

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.android.notification.datasource.IRepoNotifications

private class Notification(context: Context, body: String, title: String) {
    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Заявки"
            val descriptionText = "Ваши заявки"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel("Заявки", name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                ContextCompat.getSystemService(context, NotificationManager::class.java)

            notificationManager?.createNotificationChannel(channel)
        }
    }

    private val notification: NotificationCompat.Builder =
        NotificationCompat.Builder(context, "Заявки")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_LOW)


    fun show(context: Context, id: String) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(id.hashCode(), notification.build())
            return
        }

    }
}

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val repo: IRepoNotifications,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val n = Notification(appContext, body = "", title = "it.name")
        n.show(appContext, "54")


        repo.getNotifications().forEach {
            val n = Notification(appContext, body = it.text, title = it.name)
            n.show(appContext, it.id)
        }





        return@withContext Result.success()
    }



}