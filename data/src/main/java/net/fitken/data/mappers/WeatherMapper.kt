package net.fitken.data.mappers

import net.fitken.data.entities.WeatherData
import net.fitken.domain.entities.WeatherEntity

object WeatherMapper {
    fun mapWeatherToDomain(weatherData: WeatherData): WeatherEntity {
        return WeatherEntity(
            weatherData.id, weatherData.main, weatherData.description, weatherData.icon
        )
    }
}