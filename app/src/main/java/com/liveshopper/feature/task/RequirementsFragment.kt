package com.liveshopper.feature.task


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.lab651.liveshopper.LiveShopperAPI
import com.lab651.liveshopper.task.model.Task
import com.liveshopper.R
import com.liveshopper.feature.task.questions.BaseQuestionFragment
import kotlinx.android.synthetic.main.fragment_requirements.*
import timber.log.Timber

class RequirementsFragment : BaseQuestionFragment() {

    val checkBoxes = ArrayList<CheckBox>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requirements, container, false)
    }

    override fun populateView() {
        claimedTask?.let { task ->

            for (requirement in task.taskRequirements) {
                val checkBox = CheckBox(activity?.applicationContext)
                checkBox.setOnClickListener {
                    var totalChecked = 0
                    for (checkBox in checkBoxes) {
                        if (checkBox.isChecked) totalChecked++
                    }
                    if (totalChecked == checkBoxes.size) {
                        // Enable the GO button once all are checked
                        goButton.isEnabled = true
                        goButton.alpha = 1f
                    }
                }
                checkBox.text = requirement
                requirementContainer.addView(checkBox)
                checkBoxes.add(checkBox)
            }
            goButton.setOnClickListener {

                LiveShopperAPI.claimTask(task,
                    { task ->
                        handleClaimSubmit(task)
                    },
                    { throwable ->
                        Timber.e(throwable, "Claim submit failed!")
                    })
            }
        }

    }

    private fun handleClaimSubmit(claimedTask: Task) {
        LiveShopperAPI.claimedTask = claimedTask

        // Need to determine the next question by comparing each questions parent key
        // to the "nextQuestion" value from the claimedTask claimedTask
        claimedTask.nextQuestion?.let {
            showNextQuestionByNextKey(it)
        }
    }

}
