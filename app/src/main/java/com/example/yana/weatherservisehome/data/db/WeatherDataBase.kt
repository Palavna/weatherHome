package com.example.yana.weatherservisehome.data.db

import androidx.room.*
import com.example.yana.weatherservisehome.data.MainModel
import com.example.yana.weatherservisehome.data.current.CurrentModel
import com.example.yana.weatherservisehome.ui.DBConverter


@Database(entities = [MainModel::class, CurrentModel::class], version = 1, exportSchema = false)
@TypeConverters(DBConverter::class)
abstract class WeatherDataBase: RoomDatabase() {
    abstract fun getWeatherDao():WeatherDao
}


@Dao
interface WeatherDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveForecastWeather(model: MainModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrentWeather(model: CurrentModel)

    @Query("SELECT * FROM MainModel WHERE id=:forecastId LIMIT 1")
    fun getForecastWeather(forecastId: Long): MainModel

    @Query("SELECT * FROM CurrentModel WHERE id=:currentId LIMIT 1")
    fun getCurrentWeather(currentId: Long): CurrentModel
}