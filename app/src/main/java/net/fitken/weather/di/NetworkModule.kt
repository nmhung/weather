package net.fitken.weather.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.fitken.data.api.WeatherService
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val BASE_URL = "api.openweathermap.org/data/2.5/"

    @Provides
    fun provideRetrofit(application: Application): Retrofit {
        return createNetworkClient(BASE_URL, application)
    }

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}