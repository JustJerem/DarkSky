package com.guillotjeremie.darksky.data.di

import com.guillotjeremie.darksky.data.repository.WeatherRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    single { WeatherRepository(get()) }
}