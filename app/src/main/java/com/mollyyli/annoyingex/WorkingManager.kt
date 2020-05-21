package com.mollyyli.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class WorkingManager(private val context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startAnnoyingTheHeckOuttaPerson() {
        if (isAnnoyingExRunning()) {
            stopWork()
        }
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<AnnoyingExWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5000, TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .addTag(AE_WORK_REQUEST_TAG)
            .build()

        workManager.enqueue(workRequest)
    }

    private fun isAnnoyingExRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(AE_WORK_REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopWork() {
        workManager.cancelAllWorkByTag(AE_WORK_REQUEST_TAG)
    }


    companion object {
        const val AE_WORK_REQUEST_TAG = "AE_WORK_REQUEST_TAG"
    }
}