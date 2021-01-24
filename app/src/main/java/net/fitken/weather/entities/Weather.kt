package net.fitken.weather.entities

data class Weather(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)