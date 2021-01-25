package net.fitken.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherData(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)