package com.liveshopper.feature.task.taskoverview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lab651.liveshopper.LiveShopperAPI
import com.liveshopper.R
import com.liveshopper.feature.task.RequirementsFragment
import kotlinx.android.synthetic.main.task_overview.*

class TaskOverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populateView()
    }

    @SuppressLint("SetTextI18n", "InflateParams")
    private fun populateView() {
        LiveShopperAPI.claimedTask?.let {
            title.text = it.title
            subTitle.text = it.description
            address.text
            if (it.associatesOnly == true) {
                associateContainer.visibility = View.VISIBLE
            }

            var distance = distanceText.text as String
            distance = distance.replace("{distance}", it.distance.toString())
            distanceText.text = distance

            address.text = "${it.location.address1} ${it.location.address2}, ${it.location.city}," +
                    " ${it.location.state} ${it.location.zipCode}"

            var dReq = distanceRequirement.text as String
            dReq = dReq.replace("{distance}", it.claimDistanceOverride.toString())
            distanceRequirement.text = dReq

            rewardsText.text = "${it.rewards.size}, claimable rewards!"

            for (reward in it.rewards) {
                val rewardView = layoutInflater.inflate(R.layout.reward_item, null)
                (rewardView.findViewById(R.id.rewardTitle) as TextView).text = reward.message?.header
                (rewardView.findViewById(R.id.rewardDescription) as TextView).text = reward.message?.body

                rewardContainer.addView(rewardView)
            }

            for (question in it.questions) {
                val questionView = layoutInflater.inflate(R.layout.question_layout, null)

                (questionView.findViewById(R.id.question) as TextView).text = question.question
                questionContainer.addView(questionView)
            }

            acceptTask.setOnClickListener {
                // Launch the overview fragment.
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(android.R.id.content, RequirementsFragment())
                    ?.commit()
            }
        }

    }

}
