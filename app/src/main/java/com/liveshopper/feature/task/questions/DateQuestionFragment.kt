package com.liveshopper.feature.task.questions

import com.lab651.liveshopper.LiveShopperAPI.populateTaskResponse
import com.liveshopper.R
import kotlinx.android.synthetic.main.base_question_layout.*
import kotlinx.android.synthetic.main.date_question_layout.*

class DateQuestionFragment: BaseQuestionFragment() {

    override fun populateView() {
        super.populateView()

        addQuestionLayoutToContainer(R.layout.date_question_layout)
        currentQuestion?.let {

            val date = "${datePicker.month}/${datePicker.dayOfMonth}/${datePicker.year}"

            nextButton.setOnClickListener {
                val taskResponse = populateTaskResponse()
                taskResponse.answers.add(date)
                submitAnswer(taskResponse)
            }
        }
    }
}