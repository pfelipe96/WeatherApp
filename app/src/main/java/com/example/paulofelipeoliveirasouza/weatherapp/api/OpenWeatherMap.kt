package com.example.paulofelipeoliveirasouza.weatherapp.api

import com.example.paulofelipeoliveirasouza.weatherapp.data.OpenWeatherMapData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenWeatherMap{

    @GET("q={nameCity}&units=metric&lang=pt&APPID=15cb1793b26e3887a716da79f6f70ea0")
    fun getDataForecast(@Path("nameCity") nameCity: String): Single<OpenWeatherMapData>

}