package com.example.yana.weatherservisehome.data

import com.example.yana.weatherservisehome.data.current.CurrentModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

    interface WeatherSer {
        @GET("data/2.5/onecall")
         suspend fun getWeatherForecast(
            @Query("lat") lat: String?,
            @Query("lon") lon: String?,
            @Query("exclude") exclude: String,
            @Query("appid") appId: String,
            @Query("units") units: String,
            @Query("lang") lang: String)
                : MainModel

        @GET("data/2.5/weather")
        suspend fun getCurrentCoordinates(
            @Query("appid") appId: String,
            @Query("q") city: String
        ): MainCoordinModel

        @GET("data/2.5/weather")
        suspend fun getCurrentWeatherByCoordinates(
            @Query("appid") appId: String,
            @Query("lat") lat: Float?,
            @Query("lon") lon: Float?,
            @Query("units") units: String,
            @Query("lang") lang: String
        ): CurrentModel
    }