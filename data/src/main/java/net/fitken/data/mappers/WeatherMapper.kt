package net.fitken.data.mappers

import net.fitken.data.entities.WeatherData
import net.fitken.domain.entities.WeatherEntity

object WeatherMapper {
    fun mapMovieToDomain(weatherData: WeatherData): WeatherEntity {

        return WeatherEntity(weatherData.date
        )
    }
}