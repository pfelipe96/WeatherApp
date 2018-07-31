package com.example.paulofelipeoliveirasouza.weatherapp.di.module

import com.example.paulofelipeoliveirasouza.weatherapp.api.OpenWeatherMap
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module(includes = [ApplicationModule::class])
class ApiModule{

    @Provides
    @Named("RetrofitOpenWeatherMap")
    fun provideOpenWeatherMap(gson: Gson): Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("api.openweathermap.org/data/2.5/weather")
                .build()
    }

    @Provides
    fun provideOpenWeatherMapService(@Named("RetrofitOpenWeatherMap") openWeatherMap: Retrofit): OpenWeatherMap{
        return openWeatherMap.create(OpenWeatherMap::class.java)
    }

}