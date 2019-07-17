package com.liveshopper.feature.task.questions


import android.widget.CheckBox
import android.widget.LinearLayout
import com.lab651.liveshopper.LiveShopperAPI.populateTaskResponse
import com.liveshopper.R
import kotlinx.android.synthetic.main.base_question_layout.*

class MultipleAnswersFragment : BaseQuestionFragment() {

    private val checkBoxes: ArrayList<CheckBox> = arrayListOf()
    private val selectedAnswers = ArrayList<String>()

    override fun populateView() {
        super.populateView()

        val view = addQuestionLayoutToContainer(R.layout.multiple_choice_question_layout)
        val answerContainer = view.findViewById(R.id.answerContainer) as LinearLayout

        currentQuestion?.let { q ->
            for (answer in q.answers) {
                val checkBox = CheckBox(activity?.applicationContext)
                checkBox.setOnClickListener {
                    val answer = q.answers[checkBox.id]

                    if (checkBox.isChecked) {
                        selectedAnswers.add(answer.key)
                    } else {
                        selectedAnswers.remove(answer.key)
                    }
                }
                checkBox.text = answer.displayText
                answerContainer.addView(checkBox)

                checkBox.id = q.answers.indexOf(answer)
                checkBoxes.add(checkBox)
            }

            nextButton.setOnClickListener {
                val taskResponse = populateTaskResponse()
                for (answerKey in selectedAnswers) taskResponse.answers.add(answerKey)
                submitAnswer(taskResponse)
            }
        }
    }
}
