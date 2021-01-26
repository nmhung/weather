package net.fitken.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyForecastData(
        var city: CityData,
        var list: List<WeatherOfDayData>
)