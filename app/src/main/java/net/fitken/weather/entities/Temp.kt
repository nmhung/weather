package net.fitken.weather.entities

data class Temp(
    var day: Float,
    var min: Float,
    var max: Float,
    var night: Float,
    var eve: Float,
    var morn: Float
) {
    fun getAverage(): Float {
        return (min + max) / 2
    }
}