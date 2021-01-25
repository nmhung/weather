package net.fitken.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherOfDayData(
        var id: Int = 0,
        var dt: Long,
        var temp: TempData,
        var pressure: Int,
        var humidity: Int,
        var weather: List<WeatherData>

)