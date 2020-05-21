package com.mollyyli.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var apiManager: APIManager
    lateinit var  workingManager: WorkingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Annoying Ex"
        val annoyingexApp = application as AnnoyingExApp

        workingManager = annoyingexApp.WorkingManager
        btnHereWeGo.setOnClickListener {
            workingManager.startAnnoyingTheHeckOuttaPerson()
        }
        btnGiveClosure.setOnClickListener {
            workingManager.stopWork()
        }

    }
}
