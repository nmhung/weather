package net.fitken.domain.entities

data class TempEntity(
    var day: Float,
    var min: Float,
    var max: Float,
    var night: Float,
    var eve: Float,
    var morn: Float
)