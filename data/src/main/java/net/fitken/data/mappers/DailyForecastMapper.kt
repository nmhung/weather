package net.fitken.data.mappers

import net.fitken.data.entities.DailyForecastData
import net.fitken.domain.entities.DailyForecastEntity

object DailyForecastMapper {
    fun mapDailyForecastToDomain(dailyForecastData: DailyForecastData): DailyForecastEntity {
        val cityEntity = CityMapper.mapCityToDomain(dailyForecastData.city)
        val result = dailyForecastData.list.map {
            WeatherOfDayMapper.mapWeatherOfDayToDomain(it)
        }
        return DailyForecastEntity(
            cityEntity,
            result
        )
    }
}