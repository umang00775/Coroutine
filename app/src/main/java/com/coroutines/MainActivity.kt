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
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /* Here everything run sequentially */
        /* runBlocking{delay(1000)} is exactly same as Thread.sleep(1000) */
        Log.d(TAG, "Before of runBlocking")
        runBlocking {
            Log.d(TAG, "Starting of runBlocking")
            delay(3000L)
            Log.d(TAG, "Ending of runBlocking")
        }
        Log.d(TAG, "After of runBlocking")
    }

}