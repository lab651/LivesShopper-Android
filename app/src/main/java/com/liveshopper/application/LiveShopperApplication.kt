package com.liveshopper.application

import android.app.Application
import com.google.firebase.FirebaseApp
import com.lab651.liveshopper.LiveShopperAPI
import com.liveshopper.repository.FakeTasks
import timber.log.Timber

class LiveShopperApplication : Application() {

    val tasks by lazy { FakeTasks.buildTasks(this) }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        FirebaseApp.initializeApp(this)

        // One time init required before calling any other functions on the API singleton
        LiveShopperAPI.clientId = SAMPLE_CLIENT_ID
        LiveShopperAPI.context = applicationContext
    }

    companion object {
        const val SAMPLE_CLIENT_ID = "-LXsyvZ1knQpVZDNKzGR"
    }
}
