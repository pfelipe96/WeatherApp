package com.example.paulofelipeoliveirasouza.weatherapp.data

import com.google.gson.annotations.SerializedName

data class WeatherData(
        @SerializedName("id") var id: Int = 0,
        @SerializedName("main") var main: String = "",
        @SerializedName("description") var description: String = "",
        @SerializedName("icon") var icon: String = ""
)