package com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.presenter

import android.annotation.SuppressLint
import android.location.Location
import android.view.View
import com.example.paulofelipeoliveirasouza.weatherapp.data.OpenWeatherMapData
import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.model.MainModel
import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.ui.MainActivityInterface
import com.google.android.gms.location.places.Place
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainPresenter @Inject constructor(
        private val mainModel: MainModel) : MainPresenterInterface {

    private lateinit var reference: WeakReference<MainActivityInterface>

    init {
        mainModel.attachPresenter(this)
    }

    private val view: MainActivityInterface?
        get() = reference.get()

    override fun attachView(view: MainActivityInterface) {
        reference = WeakReference(view)
    }

    override fun getDataByCity(place: Place) {
        if (view?.isNetworkAvaliableToContext()!!) {
            view?.setVisibleFrameLayout(View.GONE)
            view?.setProgressBar(View.VISIBLE)
            view?.setVisibleMessage(View.GONE)
            mainModel.loadDataByLatAndLon(place.latLng.latitude.toString(), place.latLng.longitude.toString())
        } else {
            view?.snackBarIsNetWorking()
        }
    }

    override fun handlerOpenWeatherMap(singleCreateOpenWeather: Single<OpenWeatherMapData>) {
        singleCreateOpenWeather.subscribeBy(
                onError = {
                    view?.snackBarOnError("Cidade não encontrada, por gentileza tente novamente")
                    view?.setProgressBar(View.GONE)
                    view?.setVisibleFrameLayout(View.GONE)
                    view?.setVisibleMessage(View.VISIBLE)
                },
                onSuccess = {
                    handlerData(it)
                }
        )
    }


    @SuppressLint("SimpleDateFormat")
    private fun handlerData(data: OpenWeatherMapData) {
        val convertSunRise = Date(data.sys.sunrise.toLong() * 1000)
        val sunRise = SimpleDateFormat("HH:mm").format(convertSunRise)

        val convertSet = Date(data.sys.sunset.toLong() * 1000)
        val sunSet = SimpleDateFormat("HH:mm").format(convertSet)

        view?.setDataInViews(data.nameCity + ", " + data.sys.country,
                "${data.main.tempMin}º",
                data.weather!![0].icon,
                "${data.main.temp}º C",
                data.weather!![0].description.toUpperCase(),
                "${data.main.tempMax}º",
                "${data.main.humidity}%",
                sunRise,
                sunSet)

        view?.setProgressBar(View.GONE)
        view?.setVisibleFrameLayout(View.VISIBLE)
        view?.setVisibleMessage(View.GONE)

    }

    override fun getLocationUser(location: Location) {
        if (view?.isNetworkAvaliableToContext()!!) {
            if (location.latitude.toString().isNotEmpty() && location.longitude.toString().isNotEmpty()) {
                view?.setProgressBar(View.VISIBLE)
                view?.setVisibleMessage(View.GONE)
                mainModel.loadDataByLatAndLon(location.latitude.toString(), location.longitude.toString())
            }
        } else {
            view?.snackBarIsNetWorking()
        }
    }
}