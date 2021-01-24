package net.fitken.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.fitken.data.entities.WeatherOfDayData

open class WeatherOfDayConverter {
    @TypeConverter
    fun fromString(value: String): WeatherOfDayData? {
        val listType = object : TypeToken<WeatherOfDayData>() {}.type
        return Gson().fromJson<WeatherOfDayData>(value, listType)
    }

    @TypeConverter
    fun fromItem(item: WeatherOfDayData?): String {
        val gson = Gson()
        return gson.toJson(item)
    }
}