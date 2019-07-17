package com.liveshopper.feature.task.questions

import android.widget.EditText
import android.widget.Toast
import com.lab651.liveshopper.LiveShopperAPI.populateTaskResponse
import com.liveshopper.R
import kotlinx.android.synthetic.main.base_question_layout.*

class NumericOpenEndedFragment : BaseQuestionFragment() {

    override fun populateView() {
        super.populateView()

        addQuestionLayoutToContainer(R.layout.numeric_open_ended_layout)

        currentQuestion?.let { q ->
            nextButton.setOnClickListener {
                val answer = view?.findViewById<EditText>(R.id.answerEditText)?.text.toString()

                if (answer.isEmpty()) {
                    Toast.makeText(activity, "Enter a number before continuing!", Toast.LENGTH_LONG).show()
                } else if (!q.allowDecimals!! && answer.contains(".")) {
                    Toast.makeText(activity, "Decimals not allowed!", Toast.LENGTH_LONG).show()
                } else {
                    val taskResponse = populateTaskResponse()
                    taskResponse.userAnswer = answer
                    submitAnswer(taskResponse)
                }
            }
        }
    }
}
