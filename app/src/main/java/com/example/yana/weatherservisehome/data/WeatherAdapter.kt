package com.example.yana.weatherservisehome.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.weatherservisehome.R
import com.example.yana.weatherservisehome.databinding.ItemWeatherBinding
import com.example.yana.weatherservisehome.utils.DateTimeUtils
import com.squareup.picasso.Picasso

class WeatherAdapter() : RecyclerView.Adapter<WeatherViewHolder>() {

    private val list = arrayListOf<DailyModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addNewItem(dailyModel: List<DailyModel>?) {
        list.clear()
        list.addAll(dailyModel ?: emptyList())
        notifyDataSetChanged()
    }
}

class WeatherViewHolder(val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dailyModel: DailyModel) {
        binding.day.text = String.format(
            "%1s°", dailyModel.tempModel.day.toInt().toString()
        )
        binding.nigh.text = String.format(
            "%1s°", dailyModel.tempModel.night.toInt().toString()
        )
        binding.namber.text = DateTimeUtils.epochTimeToSdfDate(dailyModel.dt.toLong())
        val icon = dailyModel.weatherModel?.firstOrNull()?.icon
        Picasso.get().load("http://openweathermap.org/img/wn/$icon@2x.png").into(binding.imgWeather)
    }
}