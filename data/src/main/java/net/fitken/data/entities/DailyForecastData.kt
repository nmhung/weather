package net.fitken.data.entities

import com.squareup.moshi.Json

class DailyForecastData(
        var page: Int,
        @Json(name = "total_results")
        var totalResults: Int,
        @Json(name = "total_pages")
        var totalPages: Int,
)