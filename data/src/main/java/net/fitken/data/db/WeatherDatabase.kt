package net.fitken.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.fitken.data.converters.*
import net.fitken.data.entities.DataByQuery

@Database(entities = [DataByQuery::class], version = 1)
@TypeConverters(
    value = [CityConverter::class, DailyForecastConverter::class, TempConverter::class,
        WeatherConverter::class, WeatherOfDayConverter::class]
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getDataByQueryDao(): DataByQueryDao
}