package com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.presenter

import android.location.Location
import com.example.paulofelipeoliveirasouza.weatherapp.data.OpenWeatherMapData
import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.ui.MainActivityInterface
import io.reactivex.Observable
import io.reactivex.Single

interface MainPresenterInterface {

    fun attachView(view: MainActivityInterface)
    fun handlerOpenWeatherMap(singleCreateOpenWeather: Single<OpenWeatherMapData>)
    fun getDataByCity(valueObject: String)
    fun getLocationUser(location: Location)
}