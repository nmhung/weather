package net.fitken.weather.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.fitken.data.api.WeatherService
import net.fitken.data.db.WeatherLocalCache
import net.fitken.data.repositories.WeatherRepositoryImpl
import net.fitken.domain.repository.WeatherRepository
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    @Provides
    fun provideRetrofit(application: Application): Retrofit {
        return createNetworkClient(BASE_URL, application)
    }

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    fun provideWeatherRepository(
        weatherService: WeatherService,
        weatherLocalCache: WeatherLocalCache
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherService, weatherLocalCache)
    }
}