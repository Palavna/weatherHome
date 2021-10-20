package com.example.yana.weatherservisehome.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.yana.weatherservisehome.data.CoordinModel
import com.example.yana.weatherservisehome.ui.MainActivity

object PermissionUtil {


        const val LOCATION_REQUEST_CODE = 111


    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)

    fun checkLocationPermisssion(activity: AppCompatActivity): Boolean {
        if (ActivityCompat.checkSelfPermission(
                activity,
                permissions[0]
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                permissions[1]
            ) == PackageManager.PERMISSION_GRANTED
        )
            return true
        getPermission(activity)
        return false
    }

    private fun getPermission(activity: AppCompatActivity) {
        ActivityCompat.requestPermissions(activity, permissions, LOCATION_REQUEST_CODE)
    }
}