package com.example.paulofelipeoliveirasouza.weatherapp.data

import com.google.gson.annotations.SerializedName

data class SysData(
        @SerializedName("type") var type: Int = 0,
        @SerializedName("id") var id: Int = 0,
        @SerializedName("message") var message: String = "",
        @SerializedName("country") var country: String = "",
        @SerializedName("sunrise") var sunrise: Int = 0,
        @SerializedName("sunset") var sunset: Int = 0
)