package com.liveshopper.feature.task.questions

import com.lab651.liveshopper.LiveShopperAPI.populateTaskResponse
import com.liveshopper.R
import kotlinx.android.synthetic.main.question_date.*
import kotlinx.android.synthetic.main.time_question_layout.*

class TimeQuestionFragment: BaseQuestionFragment() {

    override fun populateView() {
        super.populateView()

        addQuestionLayoutToContainer(R.layout.time_question_layout)
        currentQuestion?.let {

            val date = "${timePicker.hour}:${timePicker.minute}"

            nextButton.setOnClickListener {
                val taskResponse = populateTaskResponse()
                taskResponse.answers.add(date)
                submitAnswer(taskResponse)
            }
        }
    }
}