package com.example.paulofelipeoliveirasouza.weatherapp.di.component

import com.example.paulofelipeoliveirasouza.weatherapp.MainApplication
import com.example.paulofelipeoliveirasouza.weatherapp.di.module.ApiModule
import com.example.paulofelipeoliveirasouza.weatherapp.di.module.ApplicationModule
import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.ui.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules =  [ApiModule::class, ApplicationModule::class])
interface ApplicationComponent{

    fun inject(mainApplication: MainApplication)
    fun inject(mainActivity: MainActivity)
}