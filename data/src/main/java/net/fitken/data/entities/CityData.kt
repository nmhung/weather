package net.fitken.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CityData(
    var name: String,
    var country: String,
)