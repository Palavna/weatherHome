package com.example.yana.weatherservisehome.ui.main

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.weatherservisehome.BuildConfig
import com.example.yana.weatherservisehome.data.*
import com.example.yana.weatherservisehome.data.current.CurrentModel
import com.example.yana.weatherservisehome.data.db.WeatherDao
import com.example.yana.weatherservisehome.data.db.WeatherRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: WeatherRepository): ViewModel() {

    val forecastWeather = MutableLiveData<List<DailyModel>?>()
    val progress = MutableLiveData(true)
    val iconWeather = MutableLiveData<String>()
    val currentWeather = MutableLiveData<CurrentModel?>()

    fun getForecastWeather(coordinates: CoordinModel?) {
        viewModelScope.launch {
            runCatching {
                val result = repository.getForecastWeather(
                    lat = coordinates?.lat,
                    lng = coordinates?.lon
                )
                forecastWeather.postValue(result.dailyModel)
                progress.postValue(false)
            }.onFailure {
                Log.d("aaaaaa", "sssssss")
                progress.postValue(false)
            }
        }
    }
    fun getCurrentWeather(coordinModel: CoordinModel) {
        viewModelScope.launch {
            runCatching {
                val result = repository.getCurrentWeather(
                    coordinModel.lat,
                    coordinModel.lon,
                )
                currentWeather.postValue(result)
                val icon = result.weather.firstOrNull()?.icon
                iconWeather.postValue("http://openweathermap.org/img/wn/$icon@2x.png")
            }.onFailure {
                Log.d("aaaaaa", "sssssss")
                progress.postValue(false)
            }
        }
    }
}