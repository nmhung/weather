package net.fitken.data.entities

import androidx.room.Entity

class DailyForecastData(
        var city: CityData,
        var list: List<WeatherOfDayData>
)