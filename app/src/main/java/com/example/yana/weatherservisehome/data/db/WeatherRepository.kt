package com.example.yana.weatherservisehome.data.db

import com.example.yana.weatherservisehome.BuildConfig
import com.example.yana.weatherservisehome.data.MainModel
import com.example.yana.weatherservisehome.data.WeatherInteractor
import com.example.yana.weatherservisehome.data.current.CurrentModel

class WeatherRepository(private val service: WeatherInteractor, val weatherDb: WeatherDao) {

    suspend fun getCurrentWeather(lat: Float?, lng: Float?): CurrentModel {
        val result = service.getCurrentWeather(lat, lng)
        weatherDb.saveCurrentWeather(result)
        return result
    }

    suspend fun getForecastWeather(lat:Float?, lng: Float?): MainModel {
        val result = service.getForecastWeather(lat, lng)
        weatherDb.saveForecastWeather(result)
        return result
    }
}