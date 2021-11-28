package com.example.yana.weatherservisehome.data.current

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CurrentModel(
    @Embedded
    @SerializedName("coord") val coord : Coord,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("base") val base: String,
    @Embedded
    @SerializedName("main") val main : Main,
    @SerializedName("visibility") val visibility: Int,
    @Embedded
    @SerializedName("wind") val wind : Wind,
    @Embedded
    @SerializedName("clouds") val clouds : Clouds,
    @SerializedName("dt") val dt : Int,
    @Embedded
    @SerializedName("sys") val sys : Sys,
    @SerializedName("timezone") val timezone : Int,
    @PrimaryKey
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("cod") val cod : Int

)

