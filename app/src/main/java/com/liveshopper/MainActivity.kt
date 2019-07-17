package com.liveshopper

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lab651.liveshopper.LiveShopperAPI
import com.liveshopper.feature.tabs.TabPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Need to authenticate the user before calling any of the other API methods.
        authenticateUser()
    }

    private fun authenticateUser() {
        LiveShopperAPI.login(SAMPLE_USER, SAMPLE_REDIRECT, {
            // Login success.
            if(hasNoPermissions()) requestPermission()
            setupViewPager()
        }, {
            // Login failure, try again.
            authenticateUser()
        })
    }

    private fun setupViewPager() {
        viewPager.adapter = TabPagerAdapter(this, supportFragmentManager)
    }

    private val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private fun hasNoPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    companion object {
        const val SAMPLE_USER = "dcc2c72d0dd0a0bf38d6e44684d561a6"
        const val SAMPLE_REDIRECT = "myApp://callback/"
    }
}
