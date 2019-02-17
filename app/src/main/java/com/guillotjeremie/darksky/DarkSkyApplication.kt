package com.guillotjeremie.darksky

import android.app.Application
import com.guillotjeremie.darksky.data.di.appComponent
import io.realm.Realm
import org.koin.android.ext.android.startKoin

class DarkSkyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appComponent)
        Realm.init(this)
    }
}