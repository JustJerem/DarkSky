package com.guillotjeremie.darksky.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject


open class DetailWeather(
        @SerializedName("humidity")
        var humidity: Double,
        @SerializedName("icon")
        var icon: String,
        @SerializedName("precipAccumulation")
        var precipAccumulation: Double,
        @SerializedName("precipIntensity")
        var precipIntensity: Double,
        @SerializedName("precipIntensityMax")
        var precipIntensityMax: Double,
        @SerializedName("precipIntensityMaxTime")
        var precipIntensityMaxTime: Int,
        @SerializedName("precipProbability")
        var precipProbability: Double,
        @SerializedName("precipType")
        var precipType: String,
        @SerializedName("pressure")
        var pressure: Double,
        @SerializedName("summary")
        var summary: String,
        @SerializedName("sunriseTime")
        var sunriseTime: Int,
        @SerializedName("sunsetTime")
        var sunsetTime: Int,
        @SerializedName("temperatureHigh")
        var temperatureHigh: Double,
        @SerializedName("temperatureHighTime")
        var temperatureHighTime: Int,
        @SerializedName("temperatureLow")
        var temperatureLow: Double,
        @SerializedName("temperatureLowTime")
        var temperatureLowTime: Int,
        @SerializedName("temperatureMax")
        var temperatureMax: Double,
        @SerializedName("temperatureMaxTime")
        var temperatureMaxTime: Int,
        @SerializedName("temperatureMin")
        var temperatureMin: Double,
        @SerializedName("temperatureMinTime")
        var temperatureMinTime: Int,
        @SerializedName("time")
        var time: Long
) : RealmObject() {
    constructor() : this(0.0, "", 0.0, 0.0, 0.0, 0, 0.0, "", 0.0, "", 0, 0, 0.0, 0, 0.0, 0, 0.0, 0, 0.0, 0, 0)
}