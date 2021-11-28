package com.example.yana.weatherservisehome.data

import com.example.yana.weatherservisehome.BuildConfig
import com.example.yana.weatherservisehome.data.current.CurrentModel

class WeatherInteractor(private val network: WeatherSer) {

    suspend fun getCurrentWeather(lat: Float?, lng: Float?): CurrentModel {
        return network.getCurrentWeatherByCoordinates(
            BuildConfig.weather_key,
            lat,
            lng,
            "metric",
            "ru"
        )
    }
    suspend fun getForecastWeather(lat:Float?, lng: Float?): MainModel {
        return network.getWeatherForecast(
            lat = lat.toString(),
            lon = lng.toString(),
            exclude = "minutely,hourly,alerts",
            appId = BuildConfig.weather_key,
            units = "metric",
            lang = "ru"
        )
    }
}