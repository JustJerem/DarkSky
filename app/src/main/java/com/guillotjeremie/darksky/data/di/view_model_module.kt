package com.guillotjeremie.darksky.data.di

import com.guillotjeremie.darksky.presentation.features.week.WeekViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val viewModelModule = module {
    /**
     * Instance of [WeekViewModel] who need context and WeatherRepository
     */
    viewModel { WeekViewModel(androidContext(), get()) }
}
