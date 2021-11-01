package com.example.yana.weatherservisehome.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.weatherservisehome.*
import com.example.yana.weatherservisehome.data.*
import com.example.yana.weatherservisehome.data.current.CurrentModel
import com.example.yana.weatherservisehome.databinding.ActivityMainBinding
import com.example.yana.weatherservisehome.utils.PermissionUtil
import com.example.yana.weatherservisehome.utils.PermissionUtil.LOCATION_REQUEST_CODE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
//import com.google.android.libraries.places.api.Places
//import com.google.android.libraries.places.api.model.Place
//import com.google.android.libraries.places.widget.Autocomplete
//import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var locationClient: FusedLocationProviderClient? = null
    private lateinit var recycler: RecyclerView
    private val adapter = WeatherAdapter()


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationClient = LocationServices.getFusedLocationProviderClient(this)

        if (PermissionUtil.checkLocationPermisssion(this))
            getLocation()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupViews()
        setupRecycler()

        binding.btnSearch.setOnClickListener {
//            onSearchCalled()
        }
    }

    private fun setupListeners() {
//        adapter.addNewItem()
    }

    private fun setupRecycler() {
       binding.recycView.adapter = adapter
    }



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

//        if (!Places.isInitialized()) {
//            Places.initialize(applicationContext, resources.getString(R.string.city_key))
//        }
    }
//    fun onSearchCalled(){
//        val fields: List<Place.Field> = listOf(
//            Place.Field.ID,
//            Place.Field.NAME,
//            Place.Field.ADDRESS,
//            Place.Field.LAT_LNG,
//        )
//        val intent = Autocomplete.IntentBuilder(
//            AutocompleteActivityMode.FULLSCREEN, fields
//        ).setCountry("NG")
//            .build(this)
//        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
//    }

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

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationClient?.lastLocation?.addOnCompleteListener {
            if (it.isComplete) {
                val location = CoordinModel(
                    it.result?.latitude?.toFloat(),
                    it.result?.longitude?.toFloat(),
                )
                getCurrentWeather(location)
                getForecastWeather(location)
            }
        }
    }

    private fun setCurrentWeather(data: CurrentModel?) {
        binding.nowView.updateValue(resources.getString(R.string.temperature,data?.main?.temp.toString()))
        binding.todayView.updateValue(resources.getString(R.string.temperature,data?.main?.temp_max.toString()))
        binding.minTemp.setText(resources.getString(R.string.temperature,data?.main?.temp_min.toString()))
        binding.nowView.updateWeather(resources.getString(R.string.temperature,data?.main?.feels_like.toString()))
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
                    adapter.addNewItem(response.body()?.dailyModel)
                    val data = response.body()
                    binding.countriesTv.text = String.format(
                        "%1s°C",
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
    companion object{
        const val AUTOCOMPLETE_REQUEST_CODE = 111
    }

}