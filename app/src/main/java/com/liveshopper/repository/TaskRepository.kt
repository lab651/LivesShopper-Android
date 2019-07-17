package com.liveshopper.repository

import com.lab651.liveshopper.api.ApiClient
import com.liveshopper.feature.shared.location.LocationRepository
import com.lab651.liveshopper.task.model.Task
import timber.log.Timber

/**
 * Handles fetching tasks from the Live Shopper API
 */
object TaskRepository {

    private var taskMap = HashMap<String, Task>()

    /**
     * Makes a call the API client to fetch tasks using sample parameters.
     */
    fun fetchTasks(callback: (List<Task>) -> Unit) {
        ApiClient.getAvailableTasks(
            LocationRepository.DEFAULT_LOCATION_MINNEAPOLIS.latitude.toString(),
            LocationRepository.DEFAULT_LOCATION_MINNEAPOLIS.longitude.toString(),
            "50.0",
            "50.0",
            "",
            "",
            { response ->
                for (task in response.data) {
                    taskMap[task.getId()] = task
                }
                callback(response.data)
            }, {
                Timber.d(it)
            })
    }

    fun getTask(taskId: String): Task {
        if (!taskMap.containsKey(taskId)) {
            throw Exception("No task exists with that provided Id")
        }

        return taskMap[taskId]!!
    }
}
