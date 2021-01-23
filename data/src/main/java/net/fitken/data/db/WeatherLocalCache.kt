package net.fitken.data.db

import androidx.paging.DataSource
import net.fitken.data.entities.WeatherData
import net.fitken.rose.Rose

/**
 * Class that handles the DAO local data source.
 */
class WeatherLocalCache(
        private val database: WeatherDatabase
) {

    /**
     * Insert a list of weathers in the database, on a background thread.
     */
    suspend fun insert(weathers: List<WeatherData>) {
        database.getWeatherDao().insert(weathers)
    }

    /**
     * Request a LiveData<List<WeatherData>> from the Dao.
     */
    fun getAll(): DataSource.Factory<Int, WeatherData> {
        return database.getWeatherDao().getAll()
    }

    suspend fun getWeather(id: Int): WeatherData {
        return database.getWeatherDao().getWeather(id)
    }

    suspend fun updateWeather(weatherData: WeatherData) {
        database.getWeatherDao().updateWeather(weatherData)
    }
}