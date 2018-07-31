package com.example.paulofelipeoliveirasouza.weatherapp.data

import com.google.gson.annotations.SerializedName

data class OpenWeatherMapData(
        @SerializedName("weather") var weather: ArrayList<WeatherData>? = null,
        @SerializedName("base") var base: String = "",
        @SerializedName("main") var main: MainData,
        @SerializedName("sys") var sys: SysData
)