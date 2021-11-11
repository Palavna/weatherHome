package com.example.yana.weatherservisehome.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

object DateTimeUtils {

    private val formatTime = "HH:mm"
    private val formatDate = "d"

    fun epochTimeToSdfTime(epochTime: Long? = 1635741331) = formatDate(epochTime, formatTime)
    fun epochTimeToSdfDate(epochTime: Long = 1635741331) = formatDate(epochTime, formatDate)

    fun getCurrentDate(): String {
        val calendar = getInstance()
        val date = calendar.get(DAY_OF_MONTH)
        return date.toString()
    }

    fun getCurrentYear(): String {
        val calendar = getInstance()
        val date = calendar.get(YEAR)
        return date.toString()
    }

    fun getCurrentMonth(): String {
        val calendar = getInstance()
        val date = calendar.get(MONTH) + 1
        val sdf = SimpleDateFormat("M", Locale.getDefault())
        val newSdf = SimpleDateFormat("MMMM", Locale.getDefault())
        val newDate = sdf.parse(date.toString())
        return newSdf.format(newDate)
    }

    private fun formatDate(epochTime: Long?, format: String): String{
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        val netDate = Date((epochTime ?: 0) *  1000)
        return sdf.format(netDate)
    }
}