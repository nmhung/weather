package net.fitken.weather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.fitken.domain.repository.WeatherRepository
import net.fitken.domain.usecases.GetDailyForecastUseCase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetDailyForecastUseCase(weatherRepository: WeatherRepository): GetDailyForecastUseCase {
        return GetDailyForecastUseCase(weatherRepository)
    }
}