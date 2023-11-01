package com.demo.app.capsule.fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.demo.app.capsule.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ResultFragment(private val score : String): BottomSheetDialogFragment() {

    private lateinit var scoreText : TextView
    private lateinit var parent : View

    override fun getTheme(): Int {
        return R.style.TransparentBottomSheet
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        parent = LayoutInflater.from(requireContext()).inflate(R.layout.result_fragment, container, false)

        parent.findViewById<View>(R.id.nextChapterBtn).setOnClickListener {
            activity?.let {
                val resultIntent = Intent().putExtra("CHAPTER_COMPLETED", true)
                it.setResult(Activity.RESULT_OK, resultIntent)
                it.finish()
            }
        }

        scoreText = parent.findViewById(R.id.scoreTxt)
        scoreText.text = score

        return parent
    }
}