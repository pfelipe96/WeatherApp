package com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.presenter

import android.view.View
import com.example.paulofelipeoliveirasouza.weatherapp.data.MainData
import com.example.paulofelipeoliveirasouza.weatherapp.data.OpenWeatherMapData
import com.example.paulofelipeoliveirasouza.weatherapp.data.SysData
import com.example.paulofelipeoliveirasouza.weatherapp.data.WeatherData
import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.model.MainModel
import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.ui.MainActivityInterface
import io.reactivex.Single
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.text.SimpleDateFormat
import java.util.*

class MainPresenterTest {

    @Mock
    lateinit var mockMainModel: MainModel

    @Mock
    lateinit var mockMainActivityInterface: MainActivityInterface

    lateinit var mainPresenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainPresenter = MainPresenter(mockMainModel)
        mainPresenter.attachView(mockMainActivityInterface)
    }


    @Test
    fun handlerOpenWeatherMapError() {
        mainPresenter.handlerOpenWeatherMap(getErrorSingleOpenWeather())

        verify(mockMainActivityInterface).snackBarOnError("Cidade não encontrada, por gentileza tente novamente")
        verify(mockMainActivityInterface).setProgressBar(View.GONE)
        verify(mockMainActivityInterface).setVisibleFrameLayout(View.GONE)
        verify(mockMainActivityInterface).setVisibleMessage(View.VISIBLE)
    }


    @Test
    fun handlerOpenWeatherMapSuccess() {
        mainPresenter.handlerOpenWeatherMap(getSuccessSingleOpenWeather())

        var data = getOpenWeatherData()

        val convertSunRise = Date(data.sys.sunrise.toLong() * 1000)
        val sunRise = SimpleDateFormat("HH:mm").format(convertSunRise)

        val convertSet = Date(data.sys.sunset.toLong() * 1000)
        val sunSet = SimpleDateFormat("HH:mm").format(convertSet)

        verify(mockMainActivityInterface).setDataInViews(
                data.nameCity + ", " + data.sys.country,
                "${data.main.tempMin}º",
                data.weather!![0].icon,
                "${data.main.temp}º C",
                data.weather!![0].description.toUpperCase(),
                "${data.main.tempMax}º",
                "${data.main.humidity}%",
                sunRise,
                sunSet)

        verify(mockMainActivityInterface).setProgressBar(View.GONE)
        verify(mockMainActivityInterface).setVisibleFrameLayout(View.VISIBLE)
        verify(mockMainActivityInterface).setVisibleMessage(View.GONE)
    }

    private fun getOpenWeatherData(): OpenWeatherMapData {
        return OpenWeatherMapData(
                weather = getWeatherData(),
                base = "station",
                main = getMainData(),
                sys = getSysData(),
                nameCity = "São Paulo"
        )
    }

    private fun getSuccessSingleOpenWeather(): Single<OpenWeatherMapData>{
        return Single.create{
            it.onSuccess(getOpenWeatherData())
        }
    }

    private fun getErrorSingleOpenWeather(): Single<OpenWeatherMapData>{
        return Single.create{
            it.onError(Throwable("Error"))
        }
    }

    private fun getOpenWeatherDataEmpty(): OpenWeatherMapData {
        return OpenWeatherMapData(
                weather = getWeatherDataEmpty(),
                base = "station",
                main = getMainDataEmpty(),
                sys = getSysDataEmpty(),
                nameCity = "São Paulo"
        )
    }

    private fun getWeatherData(): ArrayList<WeatherData> {
        return arrayListOf(
                WeatherData(
                        id = 804,
                        main = "Clouds",
                        description = "nuves quebradas",
                        icon = "04n"
                ),
                WeatherData(
                        id = 804,
                        main = "Clouds",
                        description = "nuves quebradas",
                        icon = "04n"
                )
        )
    }

    private fun getWeatherDataEmpty(): ArrayList<WeatherData> {
        return arrayListOf()
    }

    private fun getSysData(): SysData{
        return SysData(
                type = 1,
                id = 4541,
                message = "0.023",
                country = "BR",
                sunrise = 1533116487,
                sunset = 1533156267
        )
    }

    private fun getSysDataEmpty(): SysData = SysData()

    private fun getMainData(): MainData{
        return MainData(
                temp = "13.67",
                pressure = 1022,
                humidity = 82,
                tempMax = 13,
                tempMin = 14
        )
    }

    private fun getMainDataEmpty(): MainData = MainData()


}