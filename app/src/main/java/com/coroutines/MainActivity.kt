package com.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Start a coroutine */
        GlobalScope.launch(Dispatchers.Default) {
            delay(3000L)
            val networkResponse = netWorkCall()
            Log.d(TAG, networkResponse)
        }
        Log.d(TAG, "Hello, from ${Thread.currentThread().name}")
    }

    suspend fun netWorkCall(): String{
        delay(3000)
        return "This is response from the network :)"
    }
}