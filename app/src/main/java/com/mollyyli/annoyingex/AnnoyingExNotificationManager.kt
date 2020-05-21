package com.mollyyli.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class AnnoyingExNotificationManager(
    private val context: Context,
    private val listOfMessages: List<String>
) {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createFunChannel()
    }

    fun postItNote() {
        var message = listOfMessages[Random.nextInt(0, listOfMessages.size)]

        val dealsIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingDealsIntent = PendingIntent.getActivity(context, 0, dealsIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, ANNOYING_EX_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("You Know Who Again")
            .setContentText("$message")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingDealsIntent)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    private fun createFunChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notifications from ex"
            val descriptionText = "All msgs from annoying ex"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(ANNOYING_EX_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    companion object {
        const val ANNOYING_EX_CHANNEL_ID = "ANNOYINGEXID"
    }
}