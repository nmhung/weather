package net.fitken.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.fitken.data.entities.WeatherData

open class WeatherConverter {
    @TypeConverter
    fun fromString(value: String): WeatherData? {
        val listType = object : TypeToken<WeatherData>() {}.type
        return Gson().fromJson<WeatherData>(value, listType)
    }

    @TypeConverter
    fun fromItem(item: WeatherData?): String {
        val gson = Gson()
        return gson.toJson(item)
    }
}