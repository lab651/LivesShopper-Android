package com.liveshopper.feature.tasklist.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.android.gms.maps.model.LatLng
import com.lab651.liveshopper.task.model.Task

class TaskListAdapter(private val userLocation: LatLng, private val clickListener: (Task) -> Unit) :
    ListAdapter<Task, TaskViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = TaskItemView(parent.context)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.populateFrom(task, userLocation)
        with(holder.itemView) {
            setOnClickListener{clickListener(task)}
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) =
            oldItem == newItem
    }
}

