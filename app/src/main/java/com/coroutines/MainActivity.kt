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

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val job = GlobalScope.launch(Dispatchers.Default) {
            for (i in 30..40){
                Log.d(TAG, "Fibonacci for $i = ${fib(i)}") // Ex. of fibonacci because it takes much time
            }
        }

        runBlocking {
            delay(3000L)
            job.cancel()
            Log.d(TAG, "Running ${Thread.currentThread().name}")
        }
    }

    fun fib(num: Int): Int{
        if (num <= 1) return num
        return fib(num-1) + fib(num-2)
    }
}