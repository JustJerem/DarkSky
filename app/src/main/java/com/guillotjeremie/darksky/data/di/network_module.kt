package com.guillotjeremie.darksky.data.di

import com.guillotjeremie.darksky.data.api.WeatherService
import com.guillotjeremie.darksky.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    /**
     * Single instance of Retrofit for load data from DarkSky forecast
     */
    single {
        Retrofit.Builder()
            .baseUrl("https://api.darksky.net/forecast/${BuildConfig.DarkSkyAPIKey}/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory { get<Retrofit>().create(WeatherService::class.java) }
}