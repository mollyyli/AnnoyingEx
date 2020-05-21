package com.mollyyli.annoyingex

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class APIManager(context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)
    fun getListOfMessage(onMessagesReady: (AllMessages) -> Unit, onError: (() -> Unit)? = null) {
        val emailURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"

        val request = StringRequest(
            Request.Method.GET, emailURL,
            { response ->
                val gson = Gson()
                val allMessages = gson.fromJson(response, AllMessages::class.java )
                onMessagesReady(allMessages)
            },
            {
                onError?.invoke()
            }
        )

        queue.add(request)
    }
}