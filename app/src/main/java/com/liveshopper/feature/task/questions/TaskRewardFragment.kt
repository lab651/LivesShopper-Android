package com.liveshopper.feature.task.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.liveshopper.R
import kotlinx.android.synthetic.main.reward_item.view.*
import kotlinx.android.synthetic.main.reward_layout.*

class TaskRewardFragment : BaseQuestionFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reward_layout, container, false)
    }

    override fun populateView() {
        claimedTask?.let {
            for (reward in it.rewards) {
                val rewardItem = layoutInflater.inflate(R.layout.reward_item, null)
                rewardItem.rewardTitle.text = reward.message?.header
                rewardItem.rewardDescription.text = reward.message?.body
                reward_container.addView(rewardItem)

                rewardItem.setOnClickListener {
                    Toast.makeText(context, "Congratulations you have claimed a reward!", Toast.LENGTH_LONG).show()

                    // Exit back to the task list
                    it.postDelayed({ activity?.finish() }, 3000)
                }
            }
        }
    }
}