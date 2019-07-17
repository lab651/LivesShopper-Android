package com.liveshopper.feature.taskmap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lab651.liveshopper.task.model.Task
import com.liveshopper.feature.shared.location.LocationRepository
import com.liveshopper.repository.TaskRepository

class TaskMapViewModelFactory(
    private val taskRepository: TaskRepository,
    private val locationRepository: LocationRepository,
    private val tasks: List<Task>
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskMapViewModel(taskRepository, locationRepository, tasks) as T
    }
}
