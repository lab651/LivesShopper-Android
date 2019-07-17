package com.liveshopper.feature.task.questions

import android.view.View
import android.widget.Toast
import com.lab651.liveshopper.LiveShopperAPI.RATING_SCALE_TEXT_QUESTION
import com.lab651.liveshopper.LiveShopperAPI.populateTaskResponse
import com.liveshopper.R
import kotlinx.android.synthetic.main.base_question_layout.*
import kotlinx.android.synthetic.main.rating_question_layout.*

class RatingScaleFragment : BaseQuestionFragment() {

    override fun populateView() {
        super.populateView()
        currentQuestion?.let {

            addQuestionLayoutToContainer(R.layout.rating_question_layout)
            ratingBar.numStars = it.numStars

            leftRatingTextView.text = it.lowValueLabel
            rightRatingTextView.text = it.highValueLabel

            nextButton.setOnClickListener {
                if (ratingBar.rating != 0f) {
                    val taskResponse = populateTaskResponse()
                    taskResponse.userAnswer = ratingBar.rating.toString()
                    submitAnswer(taskResponse)
                } else {
                    Toast.makeText(activity!!, "Enter a rating before continuing!", Toast.LENGTH_LONG).show()
                }
            }

            if (it.type == RATING_SCALE_TEXT_QUESTION) {
                answerEditText.visibility = View.VISIBLE
            }
        }
    }

}