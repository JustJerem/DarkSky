package com.guillotjeremie.darksky.data.model

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.gson.annotations.SerializedName
import com.guillotjeremie.darksky.R
import io.realm.RealmObject


open class DetailWeather(
        @SerializedName("humidity")
        var humidity: Double,
        @SerializedName("icon")
        var icon: String,
        @SerializedName("precipIntensity")
        var precipIntensity: Double,
        @SerializedName("precipProbability")
        var precipProbability: Double,
        @SerializedName("summary")
        var summary: String,
        @SerializedName("temperatureHigh")
        var temperatureHigh: Double,
        @SerializedName("temperatureLow")
        var temperatureLow: Double,
        @SerializedName("temperatureMax")
        var temperatureMax: Double,
        @SerializedName("temperatureMin")
        var temperatureMin: Double,
        @SerializedName("windSpeed")
        var windSpeed : Double,
        @SerializedName("uvIndex")
        var uv : Int,
        @SerializedName("time")
        var time: Long

) : RealmObject() {
        constructor() : this(0.0, "", 0.0, 0.0, "", 0.0, 0.0,0.0, 0.0, 0.0, 0, 0)


        fun getDrawableIcon(context: Context) = when (icon) {
                "clear-day" -> ContextCompat.getDrawable(context, R.drawable.ic_sun)
                "clear-night" -> ContextCompat.getDrawable(context, R.drawable.ic_moon)
                "rain" -> ContextCompat.getDrawable(context, R.drawable.ic_rain)
                "snow", "sleet" -> ContextCompat.getDrawable(context, R.drawable.ic_snow)
                "fog", "cloudy" -> ContextCompat.getDrawable(context, R.drawable.ic_cloud)
                "partly-cloudy-day" -> ContextCompat.getDrawable(context, R.drawable.ic_sun_cloud)
                "partly-cloudy-night" -> ContextCompat.getDrawable(context, R.drawable.ic_moon_cloud)
                "thunderstorm" -> ContextCompat.getDrawable(context, R.drawable.ic_storm_cloud)
                "tornado", "wind" -> ContextCompat.getDrawable(context, R.drawable.ic_tornado)
                else -> ContextCompat.getDrawable(context, R.drawable.ic_sun)
        }

        fun getPercentagePrecip() = precipProbability*100
        fun getPercentageHumidity() = humidity
}