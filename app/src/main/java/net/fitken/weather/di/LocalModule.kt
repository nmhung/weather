package net.fitken.weather.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.fitken.data.db.WeatherDatabase
import net.fitken.data.db.WeatherLocalCache

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {
    private const val DATABASE_NAME = "weather"

    @Provides
    fun provideDatabase(application: Application): WeatherDatabase {
        return Room.databaseBuilder(application, WeatherDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    fun provideWeatherLocalCache(database: WeatherDatabase): WeatherLocalCache {
        return WeatherLocalCache(database)
    }

}