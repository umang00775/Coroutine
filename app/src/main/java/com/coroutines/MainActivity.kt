package com.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
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
        /* This is not the best way to start a coroutine */
        GlobalScope.launch {
            delay(3000L) /* Pause the thread for 3 seconds */
            Log.d(TAG, "Hello, from ${Thread.currentThread().name}")
        }
        Log.d(TAG, "Hello, from ${Thread.currentThread().name}")
    }
}