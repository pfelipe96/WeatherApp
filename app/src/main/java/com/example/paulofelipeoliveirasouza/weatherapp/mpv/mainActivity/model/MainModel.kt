package com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.model

import com.example.paulofelipeoliveirasouza.weatherapp.api.OpenWeatherMapApi
import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.presenter.MainPresenterInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainModel @Inject constructor(
        private val openWeatherMapApi: OpenWeatherMapApi): MainModelInterface{

    private lateinit var reference: WeakReference<MainPresenterInterface>

    private val presenter: MainPresenterInterface?
        get() = reference.get()


    override fun attachPresenter(presenter: MainPresenterInterface) {
        reference = WeakReference(presenter)
    }

    override fun loadDataOpen(nameCity: String) {
        val returnApi = openWeatherMapApi.getDataForecast(nameCity)
                .subscribeOn(IoScheduler())
                .observeOn(AndroidSchedulers.mainThread())

        presenter?.handlerOpenWeatherMap(returnApi)
    }

    override fun loadDataByLatAndLon(lat: String, lon: String) {
        val returnApi = openWeatherMapApi.getDataForecastByLatAndLon(lat, lon)
                .subscribeOn(IoScheduler())
                .observeOn(AndroidSchedulers.mainThread())

        presenter?.handlerOpenWeatherMap(returnApi)
    }

}