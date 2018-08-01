package com.example.paulofelipeoliveirasouza.weatherapp.api

import com.example.paulofelipeoliveirasouza.weatherapp.data.OpenWeatherMapData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi{

    @GET("weather")
    fun getDataForecast(
            @Query("q") nameCity: String,
            @Query("units") units: String = "metric",
            @Query("lang") language: String = "pt",
            @Query("APPID") key: String = "15cb1793b26e3887a716da79f6f70ea0"): Single<OpenWeatherMapData>


    @GET("weather")
    fun getDataForecastByLatAndLon(
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("units") units: String = "metric",
            @Query("lang") language: String = "pt",
            @Query("APPID") key: String = "15cb1793b26e3887a716da79f6f70ea0"): Single<OpenWeatherMapData>

}