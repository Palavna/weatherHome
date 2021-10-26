package com.example.yana.weatherservisehome.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.weatherservisehome.*
import com.example.yana.weatherservisehome.data.*
import com.example.yana.weatherservisehome.data.current.CurrentModel
import com.example.yana.weatherservisehome.databinding.ActivityMainBinding
import com.example.yana.weatherservisehome.utils.PermissionUtil
import com.example.yana.weatherservisehome.utils.PermissionUtil.LOCATION_REQUEST_CODE
import com.google.android.gms.location.FusedLocationProviderClient
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
    }
    private fun getCurrentWeather(coordinModel: CoordinModel) {
        RetrofitWeather.getRetrofit()?.getCurrentWeatherByCoordinates(
            BuildConfig.weather_key,
            coordinModel.lat,
            coordinModel.lon,
            "metric",
            "ru"
        )?.enqueue(object: Callback<CurrentModel>{
            override fun onResponse(
                call: Call<CurrentModel>,
                response: Response<CurrentModel>
            ) {
                if(response.isSuccessful){
                    val data = response.body()
                    setCurrentWeather(data)
                }
            }

            override fun onFailure(call: Call<CurrentModel>, t: Throwable) {
               Log.d("aaaaaaaa", "sssss")
            }

        })
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
                val location = CoordinModel(
                    it.result.latitude.toFloat(),
                    it.result.longitude.toFloat(),
                )
                getCurrentWeather(location)
                getForecastWeather(location)
            }
        }
    }

    private fun setCurrentWeather(data: CurrentModel?) {
        binding.nowView.updateValue(data?.main?.temp.toString())
        binding.todayView.updateValue(data?.main?.temp_max.toString())
        binding.minTemp.setText(data?.main?.temp_min.toString())
        binding.nowView.updateWeather(data?.main?.feels_like.toString())
        binding.windView.updateValue(data?.wind?.deg.toString())
        binding.pressureView.updateValue(data?.main?.pressure.toString())
        binding.humidityView.updateValue(data?.main?.humidity.toString())
        binding.cloudView.updateValue(data?.clouds?.all.toString())
        binding.sunriceView.updateValue(data?.sys?.sunrice.toString())
        binding.sunsetView.updateValue(data?.sys?.sunset.toString())
    }


    private fun getForecastWeather(coordinates: CoordinModel?) {
        RetrofitWeather.getRetrofit()?.getWeatherForecast(
            lat = coordinates?.lat.toString(),
            lon = coordinates?.lon.toString(),
            exclude = "minutely,hourly,alerts",
            appId = BuildConfig.weather_key,
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
                    binding.progress.isVisible = false
                }

            }

            override fun onFailure(call: Call<MainModel>, t: Throwable) {
                Log.d("aaaaaa", "sssssss")

            }

        })
    }

}