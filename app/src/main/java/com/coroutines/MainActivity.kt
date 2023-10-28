package com.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        /* This will take 6 seconds */
        GlobalScope.launch {
            val time = measureTimeMillis {
                val response1 = networkCall1()
                val response2 = networkCall2()
                Log.d(TAG, response1)
                Log.d(TAG, response2)
            }
            Log.d(TAG, "$time")
        }

        Log.d(TAG, "\n\n")

        /* This will take 3 seconds for the same operations as above */
        GlobalScope.launch {
            val time = measureTimeMillis {
                var response1: String? = null
                var response2: String? = null

                val job1 = launch { response1 = networkCall1() }
                val job2 = launch { response2 = networkCall1() }

                job1.join()
                job2.join()

                Log.d(TAG, "$response1")
                Log.d(TAG, "$response2")
            }
            Log.d(TAG, "$time")
        }


    }

    private suspend fun networkCall1(): String{
        delay(3000L)
        return "Response from server - 1"
    }

    private suspend fun networkCall2(): String {
        delay(3000L)
        return "Response from server - 2"
    }
}