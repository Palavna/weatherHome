package com.example.yana.weatherservisehome.ui.main

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.weatherservisehome.*
import com.example.yana.weatherservisehome.data.*
import com.example.yana.weatherservisehome.data.current.CurrentModel
import com.example.yana.weatherservisehome.databinding.ActivityMainBinding
import com.example.yana.weatherservisehome.utils.DateTimeUtils
import com.example.yana.weatherservisehome.utils.PermissionUtil
import com.example.yana.weatherservisehome.utils.PermissionUtil.LOCATION_REQUEST_CODE
import com.example.yana.weatherservisehome.utils.round
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
//import com.google.android.libraries.places.api.Places
//import com.google.android.libraries.places.api.model.Place
//import com.google.android.libraries.places.widget.Autocomplete
//import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var locationClient: FusedLocationProviderClient? = null
    private lateinit var recycler: RecyclerView
    private val adapter = WeatherAdapter()

    private val viewModel: MainViewModel by viewModel()


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationClient = LocationServices.getFusedLocationProviderClient(this)

        if (PermissionUtil.checkLocationPermisssion(this))
            getLocation()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupRecycler()
        setupViewModel()
        DateTimeUtils.epochTimeToSdfTime()

        binding.btnSearch.setOnClickListener {
            if (binding.etWeatherSerch?.text?.toString()?.trim()?.equals("")!!)
                else{
                locationClient?.lastLocation?.addOnCompleteListener {
                    if (it.isComplete) {
                        val location = CoordinModel(
                            it.result?.latitude?.toFloat(),
                            it.result?.longitude?.toFloat(),
                        )
                        viewModel.getCurrentWeather(location)
                        viewModel.getForecastWeather(location)
                    }
                }
                }
        }
    }

    private fun setupViewModel() {
        viewModel.iconWeather.observe(this,{
            Picasso.get().load(it).into(binding.sun)
        })
        viewModel.currentWeather.observe(this,{
            setCurrentWeather(it)
        })
        viewModel.progress.observe(this,{
            binding.progress.isVisible = it
        })
        viewModel.forecastWeather.observe(this,{
            adapter.addNewItem(it)
        })
    }

    private fun setupRecycler() {
        binding.recycView.adapter = adapter
        binding.recycView.addItemDecoration(MarginDecorator())
    }


    private fun setupViews() {
        setupCurrentDate()
        binding.nowView.updateLabel("Now")
        binding.todayView.updateLabel("Today")
        binding.todayView.updateWeather("Max")
        binding.pressureView.updateLabel("Pressure")
        binding.humidityView.updateLabel("Humidity")
        binding.cloudView.updateLabel("Cloudiness")
        binding.sunriseView.updateLabel("Sunrise")
        binding.sunsetView.updateLabel("Sunset")

    }

    private fun setupCurrentDate() {
        binding.dvaDev.text = DateTimeUtils.getCurrentDate()
        binding.tvYear.text = DateTimeUtils.getCurrentYear()
        binding.May.text = DateTimeUtils.getCurrentMonth()

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
                viewModel.getCurrentWeather(location)
                viewModel.getForecastWeather(location)
            }
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun setCurrentWeather(data: CurrentModel?) {
        binding.nowView.updateValue(
            resources.getString(
                R.string.temperature, data?.main?.temp?.round(0).toString()
            )
        )
        binding.todayView.updateValue(
            resources.getString(
                R.string.temperature, data?.main?.temp_max?.roundToInt().toString()
            )
        )
        binding.minTemp.text = resources.getString(
            R.string.temperature, data?.main?.temp_min?.round(0).toString()
        )
        binding.nowView.updateWeather(
            resources.getString(
                R.string.temperature, data?.main?.feels_like?.roundToInt().toString()
            )
        )
        binding.countriesTv.text = data?.name
        binding.windView.updateValue(
            resources.getString(R.string.veter, data?.wind?.speed.toString())
        )
        binding.pressureView.updateValue(
            resources.getString(R.string.pressureres, data?.main?.pressure.toString())
        )
        binding.humidityView.updateValue(
            resources.getString(R.string.percent, data?.main?.humidity.toString())
        )
        binding.cloudView.updateValue(
            resources.getString(R.string.percent, data?.clouds?.all.toString())
        )
        binding.sunriseView.updateValue(DateTimeUtils.epochTimeToSdfTime(data?.sys?.sunrise?.toLong()))
        binding.sunsetView.updateValue(DateTimeUtils.epochTimeToSdfTime(data?.sys?.sunset?.toLong()))
    }

    companion object {
        const val AUTOCOMPLETE_REQUEST_CODE = 111
    }

}