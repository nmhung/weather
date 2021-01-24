package net.fitken.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_by_query")
class DataByQuery(
    @PrimaryKey
    var query: String,
    var dailyForecast: DailyForecastData,
    var lastUpdated: Long
)