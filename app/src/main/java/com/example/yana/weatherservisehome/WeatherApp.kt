package com.example.yana.weatherservisehome

import android.app.Application
import com.example.yana.weatherservisehome.di.weatherModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@WeatherApp)
            weatherModules
        }
    }
}