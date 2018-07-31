package com.example.paulofelipeoliveirasouza.weatherapp.di.module

import android.app.Application
import dagger.Provides
import javax.inject.Singleton

class ApplicationModule(application: Application){
    val applicationInst = application

    @Provides
    @Singleton
    fun provideApplication(): Application{
        return applicationInst
    }
}