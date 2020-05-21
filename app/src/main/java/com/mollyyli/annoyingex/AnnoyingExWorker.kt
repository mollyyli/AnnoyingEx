package com.mollyyli.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class AnnoyingExWorker(private val context: Context, workParams: WorkerParameters): Worker(context , workParams) {
    override fun doWork(): Result {
        val notify = (applicationContext as AnnoyingExApp).AnnoyingExNotificationManager
        if (notify == null) {
            Log.i("main", "Not notifying")
        } else {
            notify.postItNote()
        }
        Log.i("main", "annoying...")
        return Result.success()
    }
}