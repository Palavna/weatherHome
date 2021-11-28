package com.example.yana.weatherservisehome.data

import com.google.gson.annotations.SerializedName

data class DailyModel (
    @SerializedName("dt") val dt: Int,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int,
    @SerializedName("moonrise") val moonrise: Int,
    @SerializedName("moonset") val moonset: Int,
    @SerializedName("moon_phase") val moon_phase: Double,
    @SerializedName("temp") val tempModel: TempModel,
    @SerializedName("feels_like") val feels_likeModel: FeelsLikeModel,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("dew_point") val dew_point: Double,
    @SerializedName("wind_speed") val wind_speed: Double,
    @SerializedName("wind_deg") val wind_deg: Int,
    @SerializedName("wind_gust") val wind_gust: Double,
    @SerializedName("weather") val weatherModel: List<WeatherModel>?,
    @SerializedName("clouds") val clouds: Int,
    @SerializedName("pop") val pop: Float,
    @SerializedName("uvi") val uvi: Double
        ){
//    constructor():this(
//        1, 1, 1, 1, 1, 2.0, TempModel(), FeelsLikeModel(), 1, 1,
//        2.0, 2.0, 1, 2.0, arrayListOf(), 1, 1F, 2.0
//    )
}