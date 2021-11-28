package com.example.yana.weatherservisehome.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitWeather {

    fun buildRetrofit(): WeatherSer {
        return Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherSer::class.java)
    }
    }