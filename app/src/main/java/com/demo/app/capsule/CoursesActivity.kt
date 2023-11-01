package com.demo.app.capsule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.demo.app.capsule.modal.Chapter
import com.demo.app.capsule.modal.Question
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class CoursesActivity  : AppCompatActivity() {

    private val chapters = ArrayList<Chapter>()

    //register result contract - takes in a URI for where to store the image and returns a success boolean
    private val chapterReader = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
              result.data?.getBooleanExtra("CHAPTER_COMPLETED", false).let {
                  // completed the Chapter or Not
                  if (it == true) Snackbar.make(window.decorView, "Hurry CHAPTER_COMPLETED, WOW", Snackbar.LENGTH_LONG).show()
              }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coures_activity)

        //Init the demo data.
        initChapter()



        findViewById<View>(R.id.bloodTopic).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)


            val gson = Gson()
            val jsonData = gson.toJson(chapters[0])
            intent.putExtra("DATA", jsonData)
            chapterReader.launch(intent)

        }

    }


    // Adding demo data.
    private fun initChapter() {
        // Sample questions
        val quizQuestions = listOf(

            Question(
                "Which planet is known as the Red Planet?",
                listOf("Earth", "Jupiter", "Mars", "Venus"),
                2
            ),
            Question(
                "What is the capital of France?",
                listOf("Berlin", "Madrid", "Paris", "Rome"),
                2
            ),

            Question(
                "How many continents are there on Earth?",
                listOf("5", "6", "7", "8"),
                0
            ),
            Question(
                "Who wrote 'Romeo and Juliet'?",
                listOf("William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"),
                0
            ),
            Question(
                "What is the largest mammal in the world?",
                listOf("African elephant", "Blue whale", "Giraffe", "Hippopotamus"),
                1
            )
        )

        val chapter = Chapter("qrE6Y0Se8bw", "notes is here",
            quizQuestions
        )

        chapters.add(chapter)
    }

}