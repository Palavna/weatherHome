package com.example.yana.weatherservisehome.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.example.yana.weatherservisehome.data.CitiesTextModel
import com.example.yana.weatherservisehome.databinding.ActivitySearchCityBinding

class SearchCityActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchCityBinding
    private val adapter by lazy { CitiesAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        setupLisneners()
    }

    private fun setupLisneners() {
        binding.etSearchCity.doAfterTextChanged {
            val text = it.toString()
            val list = getCities(text)
            adapter.submitList(list)
        }
    }

    private fun setupRecycler(){
        binding.rvCities.adapter = adapter
    }
    private fun getCities(text: String): List<CitiesTextModel> {
        if (text.isEmpty())return emptyList()
        return getCitiesData().filter {
            it.cityName.contains(text, true)
        }
    }

    private fun getCitiesData(): ArrayList<CitiesTextModel> {
        val data =  arrayListOf<CitiesTextModel>()

        data.add(CitiesTextModel("Москва"))
        data.add(CitiesTextModel("Монреал"))
        data.add(CitiesTextModel("Монако"))
        data.add(CitiesTextModel("Бишкек"))
        data.add(CitiesTextModel("Ош"))
        data.add(CitiesTextModel("Барнаул"))
        data.add(CitiesTextModel("Москва11"))
        data.add(CitiesTextModel("Монреал11"))
        data.add(CitiesTextModel("Монако11"))
        data.add(CitiesTextModel("Бишкек11"))
        data.add(CitiesTextModel("Ош11"))
        data.add(CitiesTextModel("Барнаул11"))
        data.add(CitiesTextModel("Москва22"))
        data.add(CitiesTextModel("Монреал22"))
        data.add(CitiesTextModel("Монако22"))
        data.add(CitiesTextModel("Бишкек22"))
        data.add(CitiesTextModel("Ош22"))
        data.add(CitiesTextModel("Барнаул22"))
        return data
    }
}