package com.example.yana.weatherservisehome.utils

fun Double.round(decimals: Int = 2): Int {
    return "%.${decimals}f".format(this).toInt()
}