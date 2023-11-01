package com.demo.app.capsule.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.demo.app.capsule.R
import com.demo.app.capsule.adapters.QuestionsRecyAdapter
import com.demo.app.capsule.modal.Question
import com.demo.app.capsule.modal.Quiz


class QuizFragment(private val quizQuestions: List<Question>) : Fragment() {

    private lateinit var parent : View
    private lateinit var questionsRecyclerView: RecyclerView
    private lateinit var viewPager2 : ViewPager2
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var questionRecyAdapter: QuestionsRecyAdapter
    private lateinit var quiz  : Quiz


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        parent = LayoutInflater.from(requireContext()).inflate(R.layout.quiz_fragment, container, false)
        questionsRecyclerView = parent.findViewById(R.id.quizQusRecView)
        viewPager2 = parent.findViewById(R.id.viewPager2)


        // Init vars
        quiz =  Quiz(quizQuestions, getCallbacks())
        questionRecyAdapter = QuestionsRecyAdapter(requireContext(), quiz)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        questionsRecyclerView.layoutManager = layoutManager
        questionsRecyclerView.adapter = questionRecyAdapter

        PagerSnapHelper().attachToRecyclerView(questionsRecyclerView)

        return parent
    }



    private fun getCallbacks(): Quiz.Callback {
          return object : Quiz.Callback {
              override fun requestNextQuestion(currentQuestionPosition: Int) {
                  Looper.getMainLooper()?.let {
                      Handler(it).postDelayed({
                          questionsRecyclerView.smoothScrollToPosition( currentQuestionPosition + 1)
                      }, 300)
                  }
              }

              override fun questionsEnded() {
                  ResultFragment("${quiz.getScore()}/${quiz.questions.size}")
                      .show(childFragmentManager, "RESULT_FRAGMENT_SHEET")
               }
          }
    }
}