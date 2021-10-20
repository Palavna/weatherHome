package com.example.yana.weatherservisehome.data

import com.google.gson.annotations.SerializedName

data class MainModel (
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_offset") val timezone_offset: Int,
    @SerializedName("current") val currentModel: CurWeatherModel,
    @SerializedName("daily") val dailyModel: List<DailyModel>
        )