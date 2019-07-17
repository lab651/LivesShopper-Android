package com.liveshopper.feature.tasklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lab651.liveshopper.task.model.Task
import com.liveshopper.feature.shared.location.LocationRepository
import com.liveshopper.repository.TaskRepository

class TaskListViewModel(
    private val taskRepository: TaskRepository,
    private val locationRepository: LocationRepository,
    private val tasks: List<Task>
) : ViewModel() {

    sealed class Action {
        class ShowTasks(val tasks: List<Task>) : Action()
    }

    val liveActions: LiveData<Action> = MutableLiveData<Action>()
    val currentLocation get() = locationRepository.currentLocation

    fun activityCreated() {
        taskRepository.fetchTasks { tasks -> Action.ShowTasks(tasks).send()}
    }

    private fun Action.send() {
        (liveActions as MutableLiveData).value = this
    }

}
