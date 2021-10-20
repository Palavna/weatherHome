package com.example.yana.weatherservisehome.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitWeather {

    private var retrofit: WeatherSer? = null

    fun getRetrofit(): WeatherSer? {
        if (retrofit == null) retrofit = buildRetrofit()
        return retrofit
    }

    private fun buildRetrofit(): WeatherSer {
        return Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherSer::class.java)
    }
}