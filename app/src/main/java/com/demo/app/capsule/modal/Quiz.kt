package com.demo.app.capsule.modal

class Quiz( val questions: List<Question>,  val callback: Callback) {

    interface Callback {
        /** @param currentQuestionPosition The question position of user is viewing now*/
        fun requestNextQuestion(currentQuestionPosition: Int)
        fun questionsEnded()
    }

    private var currentQuestionIndex = 0
    private var score = 0

    /** This Question is displaying to the user. */
    fun setCurrentQuestion(currentQuestionIndex : Int) {
        this.currentQuestionIndex = currentQuestionIndex
    }

    fun getCurrentQuestion(): Question? {
        if (currentQuestionIndex < questions.size) {
            return questions[currentQuestionIndex]
        }
        return null
    }

    fun answerQuestion(selectedAnswer: Int) {
          getCurrentQuestion()?.let {
              if (selectedAnswer == it.correctAnswer) {
                  score++
              }
              currentQuestionIndex++
          }
        }

    fun getScore(): Int {
        return score
    }

    fun getScorePercent(): Int {
        return (score / questions.size) * 100
    }

    fun isQuizComplete(): Boolean {
        return currentQuestionIndex >= questions.size
    }
}
