package com.liveshopper.feature.task.questions

import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.lab651.liveshopper.LiveShopperAPI.populateTaskResponse
import com.liveshopper.R
import kotlinx.android.synthetic.main.base_question_layout.*

class SingleAnswerFragment : BaseQuestionFragment() {

    val radioAnswerMap = HashMap<Int, String>()

    override fun populateView() {
        super.populateView()
        currentQuestion?.let {

            // Inflate the question layout for this type
            val singleChoiceLayout = addQuestionLayoutToContainer(R.layout.single_choice_question_layout)

            val radioGroup = singleChoiceLayout.findViewById(R.id.radioGroup) as RadioGroup
            for (answer in it.answers) {
                val radioButton = RadioButton(activity?.applicationContext)
                radioButton.text = answer.displayText
                radioButton.textSize = 24f
                radioButton.id = it.answers.indexOf(answer)
                radioAnswerMap[radioButton.id] = answer.key
                radioButton.setTextColor(resources.getColor(R.color.light_gray))
                radioGroup.addView(radioButton)
            }

            radioGroup.setOnCheckedChangeListener { _, _ ->
                // enable the next button
                nextButton.isEnabled = true
                nextButton.alpha = 1f
            }

            setNextButtonClickListener {
                val selectedAnswer = radioAnswerMap[radioGroup.checkedRadioButtonId]

                if (selectedAnswer != null) {
                    val taskResponse = populateTaskResponse()
                    taskResponse.answers.add(selectedAnswer)
                    submitAnswer(taskResponse)
                } else {
                    Toast.makeText(context, "Please select an option before continuing!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
