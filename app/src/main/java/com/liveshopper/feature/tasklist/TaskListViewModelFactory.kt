package com.liveshopper.feature.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lab651.liveshopper.task.model.Task
import com.liveshopper.feature.shared.location.LocationRepository
import com.liveshopper.repository.TaskRepository

class TaskListViewModelFactory(
    private val taskRepository: TaskRepository,
    private val LocationRepository: LocationRepository,
    private val tasks: List<Task>
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskListViewModel(taskRepository, LocationRepository, tasks) as T
    }
}
