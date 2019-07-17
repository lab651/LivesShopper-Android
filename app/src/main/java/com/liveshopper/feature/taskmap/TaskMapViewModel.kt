package com.liveshopper.feature.taskmap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.lab651.liveshopper.task.model.Task
import com.liveshopper.feature.location.LocationRepository
import com.liveshopper.repository.TaskRepository

class TaskMapViewModel(
    private val taskRepository: TaskRepository,
    private val locationRepository: LocationRepository,
    private val tasks: List<Task>
) : ViewModel() {

    sealed class Action {
        class ShowTasksOnMap(val tasks: List<Task>, val selectedTask: Task?) : Action()
        class MoveMapToLocation(val location: LatLng) : Action()
        class ShowDetailView(val task: Task, val userLocation: LatLng) : Action()
    }

    private val currentLocation get() = locationRepository.currentLocation

    val liveActions: LiveData<Action> = MutableLiveData<Action>()
    private var cameraPosition = currentLocation
    private var selectedTask: Task? = null
    private var firstLaunch = true

    fun mapReady() {
        if (firstLaunch) {
            locationRepository.fetchCurrentLocation { location ->
                Action.MoveMapToLocation(location).send()
            }
            firstLaunch = false
        } else {
            //Fragment was recreated after orientation change
            Action.MoveMapToLocation(cameraPosition).send()
        }

        taskRepository.fetchTasks { tasks -> Action.ShowTasksOnMap(tasks, selectedTask).send()}

        selectedTask?.let { Action.ShowDetailView(it, currentLocation).send() }
    }

    fun markerClicked(task: Task) {
        selectedTask = task
        Action.ShowDetailView(task, currentLocation).send()
    }

    fun locationPermissionGranted() {
        locationRepository.fetchCurrentLocation { location ->
            Action.MoveMapToLocation(location).send()
        }
    }

    fun cameraIdle(position: LatLng) {
        cameraPosition = position
    }

    private fun Action.send() {
        (liveActions as MutableLiveData).value = this
    }
}
