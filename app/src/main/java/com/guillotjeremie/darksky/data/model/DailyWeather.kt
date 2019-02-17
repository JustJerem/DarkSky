package com.guillotjeremie.darksky.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject


open class DailyWeather(
        @SerializedName("data")
        var detail: RealmList<DetailWeather>,
        @SerializedName("icon")
        var icon: String,
        @SerializedName("summary")
        var summary: String
) : RealmObject() {
    constructor() : this(RealmList(), "", "")
}