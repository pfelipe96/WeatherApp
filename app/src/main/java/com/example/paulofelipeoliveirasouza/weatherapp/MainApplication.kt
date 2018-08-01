package com.example.paulofelipeoliveirasouza.weatherapp

import android.app.Application
import com.example.paulofelipeoliveirasouza.weatherapp.di.component.ApplicationComponent
import com.example.paulofelipeoliveirasouza.weatherapp.di.component.DaggerApplicationComponent
import com.example.paulofelipeoliveirasouza.weatherapp.di.module.ApiModule
import com.example.paulofelipeoliveirasouza.weatherapp.di.module.ApplicationModule

class MainApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .apiModule(ApiModule())
                .build()

        applicationComponent.inject(this)
    }

}