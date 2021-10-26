package com.example.yana.weatherservisehome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.weatherservisehome.data.CitiesTextModel
import com.example.yana.weatherservisehome.databinding.ItemCityBinding

class CitiesAdapter: ListAdapter<CitiesTextModel, CitiesViewHolder>(DIFF_CITIES) {

    companion object{
        val DIFF_CITIES = object : DiffUtil.ItemCallback<CitiesTextModel>(){
            override fun areItemsTheSame(
                oldItem: CitiesTextModel,
                newItem: CitiesTextModel
            ): Boolean {
                return newItem == oldItem
            }

            override fun areContentsTheSame(
                oldItem: CitiesTextModel,
                newItem: CitiesTextModel
            ): Boolean {
                return oldItem.cityName == newItem.cityName
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        return CitiesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class CitiesViewHolder(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: CitiesTextModel) {
        binding.tvCity.text = item.cityName
    }

    companion object{
        fun create(parent: ViewGroup): CitiesViewHolder {
            val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)

            return CitiesViewHolder(binding)
        }
    }
}