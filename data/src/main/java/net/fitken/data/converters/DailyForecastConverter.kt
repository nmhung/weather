package net.fitken.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.fitken.data.entities.DailyForecastData

open class DailyForecastConverter {
    @TypeConverter
    fun fromString(value: String): DailyForecastData? {
        val listType = object : TypeToken<DailyForecastData>() {}.type
        return Gson().fromJson<DailyForecastData>(value, listType)
    }

    @TypeConverter
    fun fromItem(item: DailyForecastData?): String {
        val gson = Gson()
        return gson.toJson(item)
    }
}