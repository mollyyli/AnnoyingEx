package com.mollyyli.annoyingex

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast

class AnnoyingExApp: Application() {
    var listOfMessages: List<String>? = null

    lateinit var APIManager: APIManager

    lateinit var WorkingManager: WorkingManager
        private set

    var AnnoyingExNotificationManager: AnnoyingExNotificationManager? = null
        private set


    override fun onCreate() {

        super.onCreate()

        APIManager = APIManager(this)

        APIManager.getListOfMessage ({ allMessages ->
            listOfMessages = allMessages.messages
            Log.i("main", "api manager: ${listOfMessages}")
            AnnoyingExNotificationManager = AnnoyingExNotificationManager(this, allMessages.messages)
        },
            {
                Toast.makeText(this, "errror ", Toast.LENGTH_SHORT).show()
            })
        WorkingManager = WorkingManager(this)
    }
}