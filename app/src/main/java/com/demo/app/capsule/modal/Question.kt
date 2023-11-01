package com.demo.app.capsule.modal

data class Question(
    val text: String,
    val choices: List<String>,
    val correctAnswer: Int
)
