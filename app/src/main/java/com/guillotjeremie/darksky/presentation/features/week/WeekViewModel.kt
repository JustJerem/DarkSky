package com.guillotjeremie.darksky.presentation.features.week

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guillotjeremie.darksky.data.extension.isOnline
import com.guillotjeremie.darksky.data.model.Weather
import com.guillotjeremie.darksky.data.repository.WeatherRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class WeekViewModel(private val context: Context, private val repo: WeatherRepository) : ViewModel() {

    fun getWeekWeather(): LiveData<Weather> {
        return weekWeather
    }

    /**
     * This [WeekViewModel] keep reference on this data when view refresh (after rotation for example)
     * Just one call needed per view
     */
    private val weekWeather: MutableLiveData<Weather> by lazy {
        MutableLiveData<Weather>().also {
            getWeekWeatherData()
        }
    }

    private fun getWeekWeatherData() {
        if (context.isOnline()) {
            repo.getOnlineWeekWeather()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<Weather> {

                        override fun onCompleted() {
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }

                        override fun onNext(item: Weather) {
                            repo.save(item)
                            weekWeather.postValue(item)
                        }
                    })
        } else {
            weekWeather.postValue(repo.getOfflineWeather())
        }
    }
}