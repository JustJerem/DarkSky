package com.guillotjeremie.darksky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.guillotjeremie.darksky.presentation.features.week.WeekViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: WeekViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observe()
    }

    private fun Observe() {
        viewModel.getWeekWeather().observe(this, Observer {
            it?.let { Log.i("JG", it.timezone) }
        })
    }
}
