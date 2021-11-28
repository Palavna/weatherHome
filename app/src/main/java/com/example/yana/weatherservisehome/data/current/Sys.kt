package com.example.yana.weatherservisehome.data.current

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("type") val type : Int,
    @SerializedName("id") val idSys : Int,
    @SerializedName("country") val country : String,
    @SerializedName("sunrise") val sunrise : Int,
    @SerializedName("sunset") val sunset : Int
)
