package com.example.yana.weatherservisehome.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MainModel (
    @PrimaryKey(autoGenerate = true)
    val id:Long = -1,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_offset") val timezone_offset: Int,
    @Embedded
    @SerializedName("current") val currentModel: CurWeatherModel,
    @Embedded
    @SerializedName("tempModel") val tempModel: TempModel,
    @SerializedName("daily") val dailyModel: List<DailyModel>? = emptyList()
    )