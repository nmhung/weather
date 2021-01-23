package net.fitken.data.db

import androidx.paging.DataSource
import androidx.room.*
import net.fitken.data.entities.WeatherData

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weathers: List<WeatherData>)

    @Query("SELECT * FROM weather")
    fun getAll(): DataSource.Factory<Int, WeatherData>

    @Query("SELECT * FROM weather where id = :id")
    suspend fun getWeather(id: Int): WeatherData

    @Update
    suspend fun updateWeather(weatherData: WeatherData)
}