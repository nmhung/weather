package net.fitken.weather.mappers

import net.fitken.domain.entities.DailyForecastEntity
import net.fitken.weather.entities.DailyForecast

object DailyForecastMapper {
    fun mapDailyForecastToPresentation(dailyForecastEntity: DailyForecastEntity): DailyForecast {
        val city = CityMapper.mapCityToPresentation(dailyForecastEntity.city)
        val result = dailyForecastEntity.result.map {
            WeatherOfDayMapper.mapWeatherOfDayToPresentation(it)
        }
        return DailyForecast(
            city,
            result
        )
    }
}