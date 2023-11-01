package com.demo.app.capsule


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.demo.app.capsule.adapters.PagesAdapter
 import com.demo.app.capsule.modal.Chapter
import com.demo.app.capsule.modal.Question
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {


    private  var secondsRemaining = 3 * 60 // Minus * seconds.
    private lateinit var timerText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timerText)

        inti()


        val jsonData = intent.getStringExtra("DATA")
        if (jsonData != null) {
            val gson = Gson()
            val receivedData = gson.fromJson(jsonData, Chapter::class.java)
            // Use the received data as needed
            val viewPager = findViewById<ViewPager2>(R.id.viewPager)
            val pagerAdapter = PagesAdapter(receivedData, this)

            viewPager.adapter = pagerAdapter
        } else {
            // Handle the case where data is not received
            TODO("Handle the case, Data is not  received")
        }



    }

    private fun inti() {
         val handler = Handler(Looper.getMainLooper())
         val runnable = object : Runnable {
            override fun run() {
                if (secondsRemaining > 0) {
                    secondsRemaining--
                    updateTimerDisplay()
                    handler.postDelayed(this, 1000) // Update every second
                } else {
                   //  stopTimer()
                }
            }
        }

        // Running the timer
        runnable.run()
    }

    private fun updateTimerDisplay() {
        val minutes = secondsRemaining / 60
        val seconds = secondsRemaining % 60
        timerText.text = String.format("%02d:%02d", minutes, seconds)
    }


}