package com.guillotjeremie.darksky.di

import com.guillotjeremie.darksky.data.di.repositoryModule
import com.guillotjeremie.darksky.data.di.viewModelModule
import org.koin.dsl.module.Module


fun getComponent(): List<Module> = listOf(
    networkTest(),
    viewModelModule,
    repositoryModule
)