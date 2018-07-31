package com.example.paulofelipeoliveirasouza.weatherapp.data

import com.google.gson.annotations.SerializedName

data class MainData(
        @SerializedName("temp") var tempo: Int = 0,
        @SerializedName("pressure") var pressure: Int = 0,
        @SerializedName("humidity") var humidity: Int = 0,
        @SerializedName("temp_min") var tempMin: Int = 0,
        @SerializedName("tempo_max") var tempMax: Int = 0
)