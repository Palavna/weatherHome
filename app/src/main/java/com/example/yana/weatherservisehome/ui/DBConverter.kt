package com.example.yana.weatherservisehome.ui

import androidx.room.TypeConverter
import com.example.yana.weatherservisehome.data.DailyModel
import com.example.yana.weatherservisehome.data.WeatherModel
import com.example.yana.weatherservisehome.data.current.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DBConverter {

    @TypeConverter
    fun fromWeatherModelListToString(list: List<WeatherModel?>?): String? {
        return if (list == null) {
            null
        } else Gson().toJson(list)
    }

    @TypeConverter
    fun toWeatherModelListToString(string: String?): List<WeatherModel?>? {
        val devicesType = object :
            TypeToken<List<WeatherModel>?>() {}.type
        return Gson()
            .fromJson<List<WeatherModel>>(string, devicesType)
    }

    @TypeConverter
    fun fromDailyListToString(list: List<DailyModel?>?): String? {
        return if (list == null) {
            null
        } else Gson().toJson(list)
    }

    @TypeConverter
    fun toDailyListToString(string: String?): List<DailyModel?>? {
        val devicesType = object :
            TypeToken<List<DailyModel>?>() {}.type
        return Gson()
            .fromJson<List<DailyModel>>(string, devicesType)
    }

    @TypeConverter
    fun fromWeatherListToString(list: List<Weather?>?): String? {
        return if (list == null) {
            null
        } else Gson().toJson(list)
    }

    @TypeConverter
    fun toWeatherListToString(string: String?): List<Weather?>? {
        val devicesType = object :
            TypeToken<List<Weather>?>() {}.type
        return Gson()
            .fromJson<List<Weather>>(string, devicesType)
    }
}