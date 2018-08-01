package com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.model

import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.presenter.MainPresenterInterface

interface MainModelInterface{

    fun attachPresenter(presenter: MainPresenterInterface)
    fun loadDataOpen(nameCity: String)
    fun loadDataByLatAndLon(lat: String, lon: String)

}