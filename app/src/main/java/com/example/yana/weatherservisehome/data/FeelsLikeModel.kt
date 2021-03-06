package com.example.yana.weatherservisehome.data

import com.google.gson.annotations.SerializedName

data class FeelsLikeModel (
    @SerializedName("day") val day : Double,
    @SerializedName("night") val night : Double,
    @SerializedName("eve") val eve : Double,
    @SerializedName("morn") val morn : Double
        ){
    constructor():this(
        2.0, 2.0, 2.0, 2.0
    )
}