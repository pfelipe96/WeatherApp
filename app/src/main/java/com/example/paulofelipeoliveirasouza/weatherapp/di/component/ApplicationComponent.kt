package com.example.paulofelipeoliveirasouza.weatherapp.di.component

import com.example.paulofelipeoliveirasouza.weatherapp.di.module.ApiModule
import com.example.paulofelipeoliveirasouza.weatherapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules =  [ApiModule::class, ApplicationModule::class])
interface ApplicationComponent{

}