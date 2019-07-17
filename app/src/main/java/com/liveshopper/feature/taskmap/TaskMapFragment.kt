package com.liveshopper.feature.taskmap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.lab651.liveshopper.task.model.Task
import com.liveshopper.BaseFragment
import com.liveshopper.R
import com.liveshopper.feature.shared.location.LocationRepository
import com.liveshopper.repository.TaskRepository
import com.liveshopper.util.getApplication
import com.liveshopper.util.moveCamera
import com.liveshopper.util.setMapStyle
import kotlinx.android.synthetic.main.task_map_fragment.*

/**
 * The map view of nearby deals.  Upon launch will automatically center the camera on the user's current location.
 */
class TaskMapFragment : BaseFragment(), OnMapReadyCallback {
    private val viewModel by lazy { setupViewModel() }
    private lateinit var map: GoogleMap

    private fun setupViewModel(): TaskMapViewModel {
        val tasks = getApplication().tasks
        val factory = TaskMapViewModelFactory(TaskRepository, LocationRepository(activity!!), tasks)
        return ViewModelProviders.of(this, factory).get(TaskMapViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.task_map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMapFragment()
    }

    private fun setupMapFragment() {
        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager.beginTransaction()
            .replace(R.id.mapContainer, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        setupMap()
        observeActions()
        requestPermissions()
        viewModel.mapReady()
    }

    private fun setupMap() = activity?.let {
        map.setMapStyle(it, R.raw.map_style)
        map.setOnMarkerClickListener { marker ->
            val task = marker.getTask()
            map.moveCamera(LatLng(task.location.latitude, task.location.longitude), animate = true)
            viewModel.markerClicked(task)
            false
        }
        map.setOnCameraIdleListener {
            viewModel.cameraIdle(map.cameraPosition.target)
        }
    }

    private fun updateDetailView(task: Task, userLocation: LatLng) {
        taskItem.visibility = View.VISIBLE
        taskItem.populateFrom(task, userLocation)
    }

    private fun observeActions() {
        viewModel.liveActions.observe(this, Observer { action ->
            when (action) {
                is TaskMapViewModel.Action.ShowTasksOnMap -> showTasks(action.tasks, action.selectedTask)
                is TaskMapViewModel.Action.MoveMapToLocation -> moveMapToLocation(action.location)
                is TaskMapViewModel.Action.ShowDetailView -> updateDetailView(action.task, action.userLocation)
            }
        })
    }

    private fun showTasks(tasks: List<Task>, selectedTask: Task?) {
        map.clear()
        tasks.forEach { task ->
            val marker = map.addMarker(task)
            if (task.id == selectedTask?.id) {
                marker?.showInfoWindow()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun moveMapToLocation(location: LatLng) {
        map.isMyLocationEnabled = true
        map.moveCamera(location, ZOOM_LEVEL)
    }

    override fun onLocationPermissionGranted() {
        viewModel.locationPermissionGranted()
    }

    private fun requestPermissions() {
        if (!hasLocationPermission()) {
            requestLocationPermission()
        }
    }

    companion object {
        fun newInstance() = TaskMapFragment()
        private const val ZOOM_LEVEL = 16f
    }

    //Extension functions
    private fun GoogleMap.addMarker(task: Task) =

        task.location?.let { location ->
            addMarker(
                MarkerOptions()
                    .title(task.title)
                    .snippet(location.name)
                    .position(LatLng(location.latitude, location.longitude))
            ).apply { tag = task }
        }

    private fun Marker.getTask(): Task = tag as Task

}
