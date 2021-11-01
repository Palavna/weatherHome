package com.example.yana.weatherservisehome.data

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("id") val id: String,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("image") val image: String)


data class EveryDay(
@SerializedName("namber") val namber: Int,
@SerializedName("day") val day: Int,
@SerializedName("nigh") val nigh: Int
)
