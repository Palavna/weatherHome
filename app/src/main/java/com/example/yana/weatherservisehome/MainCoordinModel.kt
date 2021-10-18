package com.example.yana.weatherservisehome

import com.google.gson.annotations.SerializedName

data class MainCoordinModel (
    @SerializedName("coord")
    val coordinates: CoordinModel
)

data class CoordinModel(
    val lat: Float?,
    val lon: Float?
)