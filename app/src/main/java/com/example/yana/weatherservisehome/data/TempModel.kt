package com.example.yana.weatherservisehome.data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class TempModel (
    @SerializedName("day") val day : Double,
    @SerializedName("min") val min : Double,
    @SerializedName("max") val max : Double,
    @SerializedName("night") val night : Double,
    @SerializedName("eve") val eve : Double,
    @SerializedName("morn") val morn : Double
        ){
//    constructor():this(
//        2.0, 2.0, 2.0, 2.0, 2.0, 2.0
//    )
}