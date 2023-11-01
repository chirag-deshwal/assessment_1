package com.demo.app.capsule.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.app.capsule.fragments.EmptyFragment
import com.demo.app.capsule.fragments.NotesFragment
import com.demo.app.capsule.fragments.QuizFragment
import com.demo.app.capsule.fragments.TutorialFragment
import com.demo.app.capsule.modal.Chapter


class PagesAdapter(private val chapter: Chapter, activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TutorialFragment(chapter.tutorialVideoLink)
            1 -> NotesFragment(chapter.notes)
            2 -> QuizFragment(chapter.quizQuestions)
            else -> EmptyFragment() // Return a default fragment for out-of-bounds positions
        }
    }

    override fun getItemCount(): Int {
        return 3 // Number of fragments
    }

}
