package com.coroutines

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.textview)

        FirebaseApp.initializeApp(this@MainActivity)

        val document = Firebase.firestore
            .collection("document")
            .document("people")

        val data = Person("Umang", 20)

        GlobalScope.launch(Dispatchers.IO) {
            delay(3000L)

            document.set(data).await()  /* Uploading the document and wait */
            val umang = document.get().await().toObject(Person::class.java) /* Get the data */
            withContext(Dispatchers.Main){
                tv.text = umang.toString()
            }

        }

    }
}

data class Person(val name: String, val age: Int){}