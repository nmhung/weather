package net.fitken.data.db

import androidx.paging.DataSource
import androidx.room.*
import net.fitken.data.entities.WeatherOfDayData

@Dao
interface WeatherDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(weatherOfDays: List<WeatherOfDayData>)
//
//    @Query("SELECT * FROM weather")
//    fun getAll(): DataSource.Factory<Int, WeatherOfDayData>
//
//    @Query("SELECT * FROM weather where id = :id")
//    suspend fun getWeather(id: Int): WeatherOfDayData
//
//    @Update
//    suspend fun updateWeather(weatherOfDayData: WeatherOfDayData)
}