package com.example.yana.weatherservisehome.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.weatherservisehome.*
import com.example.yana.weatherservisehome.data.*
import com.example.yana.weatherservisehome.databinding.ActivityMainBinding
import com.example.yana.weatherservisehome.utils.PermissionUtil
import com.example.yana.weatherservisehome.utils.PermissionUtil.LOCATION_REQUEST_CODE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var locationClient: FusedLocationProviderClient? = null
    private lateinit var recycler: RecyclerView


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationClient = LocationServices.getFusedLocationProviderClient(this)

        if (PermissionUtil.checkLocationPermisssion(this))
            getLocation()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setupListeners()
        setupViews()
        setupRecycler()
        BuildConfig.weather_key
    }

    private fun setupRecycler() {
//        val adapter = WeatherAdapter(getData())
//        recycler.adapter = adapter
    }

//    private fun getData(): ArrayList<WeatherModel> {
//        val list = arrayListOf<WeatherModel>()
//    }


    private fun setupViews() {
        binding.recycView
        binding.nowView.updateLabel("Now")
        binding.todayView.updateLabel("Today")
        binding.todayView.updateWeather("Max")
        binding.pressureView.updateLabel("Pressure")
        binding.humidityView.updateLabel("Humidity")
        binding.cloudView.updateLabel("Cloudiness")
        binding.sunriceView.updateLabel("Sunrice")
        binding.sunsetView.updateLabel("Sunset")
        binding.airIndexView.updateLabel("Air Quality Index")
        binding.airQualityView.updateLabel("Air Quality")
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                getLocation()
            }
        }
    }

//    private fun setupListeners() {
//        binding.btnWeather.setOnClickListener {
//            getCurrentWeather()
//
//        }
//    }
//
//    private fun getCurrentWeather() {
//        val city = binding.countriesEt.text.toString().trim()
//        RetrofitWeather.getRetrofit()?.getCurrentCoordinates(
//            city = city,
//            appId = BuildConfig.weather_key
//        )?.enqueue(object : Callback<MainCoordinModel> {
//            override fun onResponse(
//                call: Call<MainCoordinModel>,
//                response: Response<MainCoordinModel>
//            ) {
//                if (response.isSuccessful) {
//                    getWeather(response.body()?.coordinates)
//                }
//            }
//
//            override fun onFailure(call: Call<MainCoordinModel>, t: Throwable) {
//
//            }
//        })
//    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationClient?.lastLocation?.addOnCompleteListener {
            if (it.isComplete) {
                getWeather(
                    CoordinModel(
                        it.result.latitude.toFloat(),
                        it.result.longitude.toFloat()
                    )
                )
            }
        }
    }

    private fun getWeather(coordinates: CoordinModel?) {
        RetrofitWeather.getRetrofit()?.getWeatherCurrent(
            lat = coordinates?.lat.toString(),
            lon = coordinates?.lon.toString(),
            exclude = "minutely,hourly,alerts",
            appId = "3c3610f480614ecde86c792f6ca681e2",
            units = "metric",
            lang = "ru"

        )?.enqueue(object : Callback<MainModel> {
            override fun onResponse(
                call: Call<MainModel>,
                response: Response<MainModel>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    binding.countriesTv.text = String.format(
                        resources.getString(R.string.temperature),
                        data?.currentModel?.temp?.toInt().toString()
                    )
                    binding.nowView.updateValue(data?.currentModel?.temp.toString())
                    binding.todayView.updateValue(data?.tempModel?.max.toString())
                    binding.nowView.updateWeather(data?.currentModel?.feels_like.toString())
                    binding.windView.updateValue(data?.currentModel?.wind_deg.toString())
                    binding.pressureView.updateValue(data?.currentModel?.pressure.toString())
                    binding.humidityView.updateValue(data?.currentModel?.humidity.toString())
                    binding.cloudView.updateValue(data?.currentModel?.clouds.toString())
                    binding.sunriceView.updateValue(data?.currentModel?.sunrise.toString())
                    binding.sunsetView.updateValue(data?.currentModel?.sunset.toString())
//                    binding.airIndexView.updateValue(data?.currentModel?.)
//                    binding.airQualityView.updateValue(data?.currentModel?.)
                    binding.progress.isVisible = false
                }

            }

            override fun onFailure(call: Call<MainModel>, t: Throwable) {
                Log.d("aaaaaa", "sssssss")

            }

        })
    }

}