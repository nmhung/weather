package net.fitken.data.mappers

import net.fitken.data.entities.WeatherOfDayData
import net.fitken.domain.entities.WeatherOfDayEntity

object WeatherOfDayMapper {
    fun mapWeatherOfDayToDomain(weatherOfDayData: WeatherOfDayData): WeatherOfDayEntity {

        val temp = TempMapper.mapTempToDomain(weatherOfDayData.temp)
        val weather = weatherOfDayData.weather.map { WeatherMapper.mapWeatherToDomain(it) }

        return WeatherOfDayEntity(
            weatherOfDayData.id,
            weatherOfDayData.dt,
            temp,
            weatherOfDayData.pressure,
            weatherOfDayData.humidity,
            weather
        )
    }
}