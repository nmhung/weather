package net.fitken.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.fitken.data.entities.CityData

open class CityConverter {
    @TypeConverter
    fun fromString(value: String): CityData? {
        val listType = object : TypeToken<CityData>() {}.type
        return Gson().fromJson<CityData>(value, listType)
    }

    @TypeConverter
    fun fromItem(item: CityData?): String {
        val gson = Gson()
        return gson.toJson(item)
    }
}