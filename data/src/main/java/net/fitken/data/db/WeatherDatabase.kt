package net.fitken.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.fitken.data.entities.WeatherOfDayData

//@Database(entities = [WeatherOfDayData::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao
}