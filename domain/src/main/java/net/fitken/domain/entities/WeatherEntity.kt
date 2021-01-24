package net.fitken.domain.entities

data class WeatherEntity(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)
