package net.fitken.data.entities

import com.squareup.moshi.Json

class DailyForecastData(
        @Json(name = "list")
        var result: List<WeatherData>
)