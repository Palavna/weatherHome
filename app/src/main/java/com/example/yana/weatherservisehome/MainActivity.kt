package com.example.yana.weatherservisehome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.yana.weatherservisehome.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnWeather.setOnClickListener {
            getCurrentWeather()
        }
    }

    private fun getCurrentWeather() {
        val city = binding.countriesEt.text.toString().trim()
        RetrofitWeather.getRetrofit()?.getCurrentCoordinates(
             city = city,
             appId = "3c3610f480614ecde86c792f6ca681e2"
        )?.enqueue(object : Callback<MainCoordinModel> {
                override fun onResponse(
                    call: Call<MainCoordinModel>,
                    response: Response<MainCoordinModel>
                ) {
                    if (response.isSuccessful){
                        getWeather(response.body()?.coordinates)
                    }
                }

                override fun onFailure(call: Call<MainCoordinModel>, t: Throwable) {

                }
            })
    }
    private fun getWeather(coordinates: CoordinModel?) {
        RetrofitWeather.getRetrofit()?.getWeatherCurrent(
            lat = coordinates?.lat.toString(),
            lon = coordinates?.lon.toString(),
            exclude = "minutely,hourly,alerts",
            appId = "3c3610f480614ecde86c792f6ca681e2",
            units = "metric",
            lang = "ru"

        )?.enqueue(object: Callback<MainCoordinModel>
        {
            override fun onResponse(
                call: Call<MainCoordinModel>,
                response: Response<MainCoordinModel>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MainCoordinModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}