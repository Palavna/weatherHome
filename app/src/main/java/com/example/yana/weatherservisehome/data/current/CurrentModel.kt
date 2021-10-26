package com.example.yana.weatherservisehome.data.current

import com.example.yana.weatherservisehome.data.MainModel
import com.google.android.gms.awareness.state.Weather
import com.google.gson.annotations.SerializedName

data class CurrentModel(
    @SerializedName("coord") val coord : Coord,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main : Main,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind : Wind,
    @SerializedName("clouds") val clouds : Clouds,
    @SerializedName("dt") val dt : Int,
    @SerializedName("sys") val sys : Sys,
    @SerializedName("timezone") val timezone : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("cod") val cod : Int

)
