package com.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.textView)

        /* Start a coroutine */
        GlobalScope.launch(Dispatchers.IO) {  /* Not in Main thread */
            delay(3000L)
            val networkResponse = netWorkCall()
            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
            withContext(Dispatchers.Main){ /* Change context to main thread */
                Log.d(TAG, "Setting coroutine in thread ${Thread.currentThread().name}")
                tv.text = networkResponse
            }
        }

        Log.d(TAG, "Hello, from ${Thread.currentThread().name}")
    }

    suspend fun netWorkCall(): String{
        delay(3000)
        return "This is response from the network :)"
    }
}