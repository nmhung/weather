package net.fitken.domain.entities


class DailyForecastEntity(
        var city: CityEntity,
        var result: List<WeatherOfDayEntity>
)