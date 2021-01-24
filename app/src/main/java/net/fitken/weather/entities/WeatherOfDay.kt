package net.fitken.weather.entities

data class WeatherOfDay(
        var id: Int = 0,
        var dt: Long,
        var temp: Temp,
        var pressure: Int,
        var humidity: Int,
        var weather: List<Weather>

)