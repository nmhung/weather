package net.fitken.data.db

import androidx.paging.DataSource
import net.fitken.data.entities.WeatherOfDayData

/**
 * Class that handles the DAO local data source.
 */
class WeatherLocalCache(
        private val database: WeatherDatabase
) {

    /**
     * Insert a list of weathers in the database, on a background thread.
     */
//    suspend fun insert(weatherOfDays: List<WeatherOfDayData>) {
//        database.getWeatherDao().insert(weatherOfDays)
//    }

    /**
     * Request a LiveData<List<WeatherData>> from the Dao.
     */
//    fun getAll(): DataSource.Factory<Int, WeatherOfDayData> {
//        return database.getWeatherDao().getAll()
//    }

//    suspend fun getWeather(id: Int): WeatherOfDayData {
//        return database.getWeatherDao().getWeather(id)
//    }

//    suspend fun updateWeather(weatherOfDayData: WeatherOfDayData) {
//        database.getWeatherDao().updateWeather(weatherOfDayData)
//    }
}