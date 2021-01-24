package net.fitken.weather.mappers

import net.fitken.domain.entities.WeatherEntity
import net.fitken.weather.entities.Weather

object WeatherMapper {
    fun mapWeatherToPresentation(weatherEntity: WeatherEntity): Weather {
        return Weather(
            weatherEntity.date
        )
    }
}