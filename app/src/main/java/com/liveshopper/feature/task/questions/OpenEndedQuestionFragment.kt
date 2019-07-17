package com.liveshopper.feature.task.questions


import com.lab651.liveshopper.LiveShopperAPI.populateTaskResponse
import com.liveshopper.R
import kotlinx.android.synthetic.main.base_question_layout.*
import kotlinx.android.synthetic.main.text_question_layout.*

class OpenEndedQuestionFragment : BaseQuestionFragment() {

    override fun populateView() {
        super.populateView()

        addQuestionLayoutToContainer(R.layout.text_question_layout)

        nextButton.setOnClickListener {
            if(answerEditText.text.isNotEmpty()) {
                val taskResponse = populateTaskResponse()
                taskResponse.userAnswer = answerEditText.text.toString()
                submitAnswer(taskResponse)
            }
        }
    }

}
