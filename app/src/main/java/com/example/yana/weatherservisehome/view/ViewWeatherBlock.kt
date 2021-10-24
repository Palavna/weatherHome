package com.example.yana.weatherservisehome.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.yana.weatherservisehome.R

class ViewWeatherBlock @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0 ,
    defStyleRec: Int = 0
): LinearLayout(context,attrs, defStyle, defStyleRec) {

    private var tvLabel: TextView? = null
    private var tvValue: TextView? = null
    private var tvWeather: TextView? = null


    init {
        val view = View.inflate(context, R.layout.view_weather_block, this)
        tvLabel = view.findViewById(R.id.wind)
        tvValue = view.findViewById(R.id.windResult)
        tvWeather = view.findViewById(R.id.nowWeather)
    }

    fun updateLabel(textOfLabel:String){
        tvLabel?.text = textOfLabel
    }
    fun updateValue(textOfValue:String){
        tvValue?.text = textOfValue
    }
    fun updateWeather(textOfWeather:String){
        tvWeather?.text = textOfWeather
    }
}