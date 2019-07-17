package com.liveshopper.feature.tasklist.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.gms.maps.model.LatLng
import com.lab651.liveshopper.task.model.Task
import com.liveshopper.R
import com.liveshopper.util.Util
import com.liveshopper.util.distanceTo
import com.liveshopper.util.loadUrl
import kotlinx.android.synthetic.main.task_item.view.*

class TaskItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.task_item, this, true)
        view.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    fun populateFrom(task: Task, userLocation: LatLng) {
        detailTitle.text = task.title
        detailName.text = task.location.name
        detailReward.text = task.rewards.run {
            if (size == 1) {
                get(0).message?.header ?: ""
            } else {
                context.getString(R.string.claimable_rewards, size)
            }
        }
        detailDistance.text = task.run {
            val distance = userLocation.distanceTo(LatLng(location.latitude, location.longitude))
            val miles = "%.2f".format(Util.metersToMiles(distance))
            context.getString(R.string.miles_away, miles)
        }
        task.logo?.let { detailImage.loadUrl(it) }
    }
}
