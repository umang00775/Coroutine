package com.coroutines

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
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

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            /* This will run while the app is alive, which can cause a memory leaks */
            /* Hence we use lifecycle for this, It will run while the Activity is alive */
            GlobalScope.launch {
                /* This will continue running even we finish this and start another activity */
                while (true){
                    delay(1000L)
                    Log.d(TAG, "Still running ${Thread.currentThread().name}")
                }
            }

            /* This will run, only when this activity is alive */
            lifecycleScope.launch {
                while (true){
                    delay(1000L)
                    Log.d(TAG, "Running ${Thread.currentThread().name}")
                }
            }

            GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()  /* Even after finish the upper while loop continues to run */
                }

            }
        }

    }
}