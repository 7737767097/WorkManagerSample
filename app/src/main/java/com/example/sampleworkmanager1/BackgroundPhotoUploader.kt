package com.example.sampleworkmanager1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class BackgroundPhotoUploader(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    val TAG = "BackgroundPhotoUploader"

    override fun doWork(): Result {
        Log.d(TAG, "Uploading Photos")
        displayNotification("task", "desc")
        return Result.retry()
    }

    fun displayNotification(task: String, desc: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("Simple", "Simple", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        val notificationCompant = NotificationCompat.Builder(applicationContext, "Simple")
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.mipmap.ic_launcher)

        notificationManager.notify(1, notificationCompant.build())
    }
}