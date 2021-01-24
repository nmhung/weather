package net.fitken.data.db

import net.fitken.data.entities.DataByQuery

/**
 * Class that handles the DAO local data source.
 */
class WeatherLocalCache(
        private val database: WeatherDatabase
) {

        /**
         * Insert a weather data by query in the database, on a background thread.
         */
        suspend fun insert(dataByQuery: DataByQuery) {
                database.getDataByQueryDao().insert(dataByQuery)
        }

        suspend fun getDataByQuery(query: String): DataByQuery? {
                return database.getDataByQueryDao().getDataByQuery(query)
        }

        suspend fun updateDataByQuery(dataByQuery: DataByQuery) {
                database.getDataByQueryDao().updateDataByQuery(dataByQuery)
        }
}