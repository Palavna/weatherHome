package com.example.yana.weatherservisehome.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yana.weatherservisehome.data.RetrofitWeather
import com.example.yana.weatherservisehome.data.WeatherInteractor
import com.example.yana.weatherservisehome.data.db.WeatherDataBase
import com.example.yana.weatherservisehome.data.db.WeatherRepository
import com.example.yana.weatherservisehome.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


val weatherModules by lazy {
    loadKoinModules(
        listOf(
            networkModule,
            viewModelModule,
            interactorModules,
            dbModule,
            repositoryModel
        )
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
val dbModule = module {
    single { Room.databaseBuilder(get(), WeatherDataBase::class.java, "weather")
        .allowMainThreadQueries()
        .build()
    }
    single { get<WeatherDataBase>().getWeatherDao()}
}

val repositoryModel = module {
    single { WeatherRepository(get(), get()) }
}