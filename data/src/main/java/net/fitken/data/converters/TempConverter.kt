package net.fitken.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.fitken.data.entities.TempData

open class TempConverter {
    @TypeConverter
    fun fromString(value: String): TempData? {
        val listType = object : TypeToken<TempData>() {}.type
        return Gson().fromJson<TempData>(value, listType)
    }

    @TypeConverter
    fun fromItem(item: TempData?): String {
        val gson = Gson()
        return gson.toJson(item)
    }
}