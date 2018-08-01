package com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.ui


interface MainActivityInterface{

    fun setDataInViews(
            nameCity: String,
            minTemperature: String,
            setImage: String,
            nowTemperature: String,
            forecast: String,
            maxTemperature: String,
            humidity: String,
            sunrise: String,
            sunset: String)

    fun setProgressBar(visible: Int)
    fun setVisibleFrameLayout(visible: Int)
    fun getLocation()
    fun getPermissionLocation()
    fun isNetworkAvaliableToContext(): Boolean
    fun snackBarIsNetWorking()
    fun snackBarCityNotFound(message: String)

}