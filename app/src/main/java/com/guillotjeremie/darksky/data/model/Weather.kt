package com.guillotjeremie.darksky.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Weather(
        @SerializedName("daily")
        var daily: DailyWeather?,
        @SerializedName("latitude")
        var latitude: Double,
        @SerializedName("longitude")
        var longitude: Double,
        @PrimaryKey
        @SerializedName("timezone")
        var timezone: String

) : RealmObject() {
    constructor() : this(null, 0.0, 0.0, "")
}