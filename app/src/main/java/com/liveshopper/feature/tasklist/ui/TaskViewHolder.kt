package com.liveshopper.feature.tasklist.ui

import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.lab651.liveshopper.task.model.Task

class TaskViewHolder(private val taskView: TaskItemView) : RecyclerView.ViewHolder(taskView) {

    fun populateFrom(task: Task, userLocation: LatLng) = itemView.apply {
        taskView.populateFrom(task, userLocation)
    }
}
