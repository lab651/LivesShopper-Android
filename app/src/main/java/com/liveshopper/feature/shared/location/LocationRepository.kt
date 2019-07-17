package com.liveshopper.feature.shared.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.liveshopper.util.toLatLng

class LocationRepository(context: Context) {

    private val fusedLocationClient by lazy { LocationServices.getFusedLocationProviderClient(context) }

    var currentLocation: LatLng = DEFAULT_LOCATION_MINNEAPOLIS
        private set

    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation(callback: (LatLng) -> Unit) = fusedLocationClient?.let {
        it.lastLocation.addOnSuccessListener { location ->
            location?.let { loc -> currentLocation = loc.toLatLng() }
            callback(currentLocation)
        }
    }

    companion object {
        val DEFAULT_LOCATION_MINNEAPOLIS = LatLng(44.957373, -93.1910918)

        @Volatile
        private var instance: LocationRepository? = null

        fun getInstance(context: Context): LocationRepository =
            instance ?: synchronized(this) {
                instance ?: LocationRepository(context.applicationContext).also { instance = it }
            }
    }

}
