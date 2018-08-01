package com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.presenter

import android.location.Location
import com.example.paulofelipeoliveirasouza.weatherapp.data.OpenWeatherMapData
import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.ui.MainActivityInterface
import com.google.android.gms.location.places.Place
import io.reactivex.Observable
import io.reactivex.Single

interface MainPresenterInterface {

    fun attachView(view: MainActivityInterface)
    fun handlerOpenWeatherMap(singleCreateOpenWeather: Single<OpenWeatherMapData>)
    fun getDataByCity(place: Place)
    fun getLocationUser(location: Location)
}