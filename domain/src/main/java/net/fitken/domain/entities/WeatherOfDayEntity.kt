package net.fitken.domain.entities

data class WeatherOfDayEntity(
        var id: Int = 0,
        var dt: Long,
        var temp: TempEntity,
        var pressure: Int,
        var humidity: Int,
        var weather: List<WeatherEntity>

)