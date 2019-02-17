package com.guillotjeremie.darksky.data.repository

import com.guillotjeremie.darksky.data.api.WeatherService
import com.guillotjeremie.darksky.data.model.Weather
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rx.Observable


class WeatherRepository(private val service: WeatherService) {

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    /**
     * Fetch online data if device is online
     * return [Weather] object
     */
    fun getOnlineWeekWeather(): Observable<Weather> {
        return Observable.create { subscriber ->
            CoroutineScope(Dispatchers.Default).launch {
                val weather = service.getWeekWeatherAsync().await()
                subscriber.onNext(weather)
            }
        }
    }

    /**
     * Fetch offline data when device is offline
     * "fieldName value" must be dynamic if multiple city and stored in another place like SharePref.
     * return [Weather] object
     */

    fun getOfflineWeather(): Weather? {
        return realm.where(Weather::class.java).findAll().where().equalTo("timezone", "America/New_York").findFirst()
    }

    fun save(data: Weather) {
        realm.executeTransactionAsync {
            it.insertOrUpdate(data)
        }
    }
}