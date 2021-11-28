package com.example.yana.weatherservisehome.di

import com.example.yana.weatherservisehome.data.RetrofitWeather
import com.example.yana.weatherservisehome.data.WeatherInteractor
import com.example.yana.weatherservisehome.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


val weatherModules by lazy {
    loadKoinModules(
        listOf(networkModule, viewModelModule, interactorModules)
    )
}
val networkModule = module {
    single {
        RetrofitWeather.buildRetrofit()
    }
}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}
val interactorModules = module {
    single { WeatherInteractor(get()) }
}