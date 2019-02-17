package com.guillotjeremie.darksky.di

import com.guillotjeremie.darksky.BuildConfig
import com.guillotjeremie.darksky.data.api.WeatherService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun networkTest() = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.darksky.net/forecast/${BuildConfig.DarkSkyAPIKey}/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory { get<Retrofit>().create(WeatherService::class.java) }
}