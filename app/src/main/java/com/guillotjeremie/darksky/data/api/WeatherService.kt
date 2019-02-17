package com.guillotjeremie.darksky.data.api

import com.guillotjeremie.darksky.data.model.Weather
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface WeatherService {

    @GET("$CITY_LAT,$CITY_LNG/?exclude=currently,minutely,hourly,flags")
    fun getWeekWeatherAsync(): Deferred<Weather>

    companion object {
        //New York
        const val CITY_LAT = "40.730610"
        const val CITY_LNG = "-73.935242"
    }

}