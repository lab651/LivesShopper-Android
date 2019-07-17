package com.liveshopper.util

import android.content.Context
import android.location.Location
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.liveshopper.application.GlideApp
import com.liveshopper.application.LiveShopperApplication

fun GoogleMap.setMapStyle(context: Context, styleRes: Int) {
    setMapStyle(MapStyleOptions.loadRawResourceStyle(context, styleRes))
}

fun LatLng.distanceTo(other: LatLng): Float {
    val results = FloatArray(1)
    Location.distanceBetween(
        latitude,
        longitude,
        other.latitude,
        other.longitude,
        results
    )
    return results[0]
}

fun GoogleMap.moveCamera(location: LatLng, zoomLevel: Float? = null, animate: Boolean = false) {
    var update: CameraUpdate = if (zoomLevel != null) {
        CameraUpdateFactory.newLatLngZoom(location, zoomLevel)
    } else {
        CameraUpdateFactory.newLatLng(location)
    }

    if (animate) {
        animateCamera(update)
    } else {
        moveCamera(update)
    }
}

fun Location.toLatLng() = LatLng(latitude, longitude)

fun Fragment.getApplication() = activity!!.application as LiveShopperApplication

fun ImageView.loadUrl(url: String) {
    GlideApp.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade(100))
        .into(this)
}

