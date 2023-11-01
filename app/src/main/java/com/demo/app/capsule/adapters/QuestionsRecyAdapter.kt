package com.demo.app.capsule.adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.demo.app.capsule.R
import com.demo.app.capsule.modal.Quiz

class QuestionsRecyAdapter(private val mContext: Context, private val quiz: Quiz) : RecyclerView.Adapter<QuestionsRecyAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
        val answerChoicesGroup : RadioGroup = itemView.findViewById(R.id.answerChoicesGroup)
        val showAnswerBtn : View = itemView.findViewById(R.id.newQusBtn)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view = LayoutInflater.from(mContext).inflate(R.layout.qus_row, parent, false)
         return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quiz.questions.size
     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val question = quiz.questions[position]

        holder.questionTextView.text = question.text


        try {
            // Must required 4 options to display in array list.
            holder.answerChoicesGroup.findViewById<RadioButton>(R.id.option1).apply { text = question.choices[0] }
            holder.answerChoicesGroup.findViewById<RadioButton>(R.id.option2).apply { text = question.choices[1] }
            holder.answerChoicesGroup.findViewById<RadioButton>(R.id.option3).apply { text = question.choices[2] }
            holder.answerChoicesGroup.findViewById<RadioButton>(R.id.option4).apply { text = question.choices[3] }
        }catch (e : IndexOutOfBoundsException) {
            e.printStackTrace()
        }


        holder.showAnswerBtn.setOnClickListener {
            showHintDialog(mContext, question.choices[question.correctAnswer])
        }

        holder.answerChoicesGroup.let {
            it.setOnCheckedChangeListener { _, checkedId ->
                //Setting the current question IF users scrolled this QUS manually
                quiz.setCurrentQuestion(holder.absoluteAdapterPosition)

                val radioButton = it.findViewById<RadioButton>(checkedId)
                val selectedPosition = holder.answerChoicesGroup.indexOfChild(radioButton)


                if (selectedPosition != -1) {
                    // The selected position is in 'selectedPosition'
                    // You can use this position to identify the selected option.
                    // 'selectedPosition' is 0-based, meaning the first RadioButton is at position 0.
                    quiz.answerQuestion(selectedPosition)

                    if (quiz.isQuizComplete()) quiz.callback.questionsEnded()
                    else  quiz.callback.requestNextQuestion(holder.absoluteAdapterPosition)

                }
            }
        }
     }

    private fun showHintDialog(mContext: Context, text :String) {
        Dialog(mContext).apply {
            setContentView(R.layout.show_hint_sheet)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setGravity(Gravity.BOTTOM)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT )
            findViewById<TextView>(R.id.answerText).text = text

            show()
        }
    }

}
