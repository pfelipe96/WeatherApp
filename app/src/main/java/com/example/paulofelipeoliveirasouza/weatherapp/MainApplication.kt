package com.example.paulofelipeoliveirasouza.weatherapp

import android.app.Application
import com.example.paulofelipeoliveirasouza.weatherapp.di.component.ApplicationComponent
import com.example.paulofelipeoliveirasouza.weatherapp.di.component.DaggerApplicationComponent

class MainApplication: Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
                .builder()
                .build()
    }

}