package net.fitken.weather.mappers

import net.fitken.domain.entities.WeatherOfDayEntity
import net.fitken.weather.entities.WeatherOfDay

object WeatherOfDayMapper {
    fun mapWeatherOfDayToPresentation(weatherOfDayEntity: WeatherOfDayEntity): WeatherOfDay {

        val temp = TempMapper.mapTempToPresentation(weatherOfDayEntity.temp)
        val weather = weatherOfDayEntity.weather.map { WeatherMapper.mapWeatherToPresentation(it) }

        return WeatherOfDay(
            weatherOfDayEntity.id,
            weatherOfDayEntity.dt,
            temp,
            weatherOfDayEntity.pressure,
            weatherOfDayEntity.humidity,
            weather
        )
    }
}