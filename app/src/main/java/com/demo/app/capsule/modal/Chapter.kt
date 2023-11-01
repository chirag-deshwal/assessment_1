package com.demo.app.capsule.modal

data class Chapter(
    val tutorialVideoLink : String,
    val notes : String, // HTML formatted text for notes
    val quizQuestions:  List<Question>
    )