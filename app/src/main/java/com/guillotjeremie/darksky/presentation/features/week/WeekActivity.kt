package com.guillotjeremie.darksky.presentation.features.week

import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guillotjeremie.darksky.R
import com.guillotjeremie.darksky.data.extension.toCompleteLetterDate
import com.guillotjeremie.darksky.data.model.DetailWeather
import com.guillotjeremie.darksky.data.model.Weather
import kotlinx.android.synthetic.main.activity_week.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.header_week.*
import java.io.IOException
import java.util.*
import kotlin.math.roundToInt


interface WeekListener {
    fun showDetail(message: String)
}

class WeekActivity : AppCompatActivity(), WeekListener {

    private val viewModel: WeekViewModel by viewModel()
    private val weekAdapter = WeekAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week)

        initRecyclerView()
        initObservers()
    }


    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        recycler_week.layoutManager = layoutManager
        recycler_week.adapter = weekAdapter
    }

    private fun initObservers() {
        viewModel.getWeekWeather().observe(this, Observer {
            it?.let { weather ->
                loader.visibility = View.GONE
                updateAdapter(weather)
                initCurrentDay(weather.daily?.detail?.get(0))
                initPlace(weather)
            }
        })
    }

    private fun initPlace(weather: Weather) {
        val geocoder = Geocoder(this, Locale.US)
        try {
            val address = geocoder.getFromLocation(weather.latitude, weather.longitude, 1)[0]
            txt_region_name.text = address.countryName
            txt_city_name.text = address.locality
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun updateAdapter(weather: Weather) {
        val items = weather.daily?.detail?.map { it } as ArrayList<DetailWeather>
        if (items.size > 0) {
            items.removeAt(0)
            weekAdapter.setItems(items)
        }
    }

    private fun initCurrentDay(detail: DetailWeather?) {
        detail?.let { weather ->
            val summary = "${weather.time.toCompleteLetterDate()}, ${weather.summary}"
            val average = "${((weather.temperatureMax + weather.temperatureMin) / 2).roundToInt()}°"
            val low = "${weather.temperatureMin.roundToInt()}${getString(R.string.low)}"
            val high = "${weather.temperatureMax.roundToInt()}${getString(R.string.high)}"
            val precipitation = "${weather.getPercentagePrecip()}%"
            val humidity = "${weather.getPercentageHumidity()}%"
            val speed = "${weather.windSpeed}${getString(R.string.mph)}"


            txt_current_day.text = summary
            txt_average_temperature_current_day.text = average
            txt_average_temperature_current_day.setOnClickListener { showDetail(weather.summary) }
            img_weather_current_day.setImageDrawable(weather.getDrawableIcon(this))
            txt_low_temperature_current_day.text = low
            txt_high_temperature_current_day.text = high
            txt_precipitation.text = precipitation
            txt_humidity.text = humidity
            txt_wind.text = speed
            txt_uv.text = weather.uv.toString()
        }
    }

    override fun showDetail(message: String) {
        AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}


