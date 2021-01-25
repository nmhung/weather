package net.fitken.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TempData(
    var day: Float,
    var min: Float,
    var max: Float,
    var night: Float,
    var eve: Float,
    var morn: Float
)