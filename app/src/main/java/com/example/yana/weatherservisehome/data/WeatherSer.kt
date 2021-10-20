package com.example.yana.weatherservisehome.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

    interface WeatherSer {
        @GET("data/2.5/weather")
        fun getWeatherCurrent(
            @Query("lat") lat: String?,
            @Query("lon") lon: String?,
            @Query("exclude") exclude: String,
            @Query("appid") appId: String,
            @Query("units") units: String,
            @Query("lang") lang: String)
                : Call<CurWeatherModel>

        @GET("data/2.5/weather")
        fun getCurrentCoordinates(
            @Query("appid") appId: String,
            @Query("q") city: String
        ): Call<MainCoordinModel>
    }