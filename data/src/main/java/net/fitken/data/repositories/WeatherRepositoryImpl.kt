package net.fitken.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import net.fitken.data.api.WeatherService
import net.fitken.data.common.Constants
import net.fitken.data.db.WeatherLocalCache
import net.fitken.data.entities.DataByQuery
import net.fitken.data.mappers.DailyForecastMapper
import net.fitken.domain.common.Interactor
import net.fitken.domain.entities.DailyForecastEntity
import net.fitken.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val mService: WeatherService,
    private val mCache: WeatherLocalCache
) : WeatherRepository {
    override suspend fun getDailyForecast(
        coroutineScope: CoroutineScope,
        interactor: Interactor, query: String
    ): LiveData<DailyForecastEntity> = liveData {
        val cache = mCache.getDataByQuery(query)
        val lastUpdated = cache?.lastUpdated ?: 0
        cache?.let {
            emit(DailyForecastMapper.mapDailyForecastToDomain(it.dailyForecast))
        }
        if (System.currentTimeMillis() - lastUpdated > Constants.DATA_REFRESH_CYCLE) {
            try {
                interactor.loading(true)
                val dailyForecastData = mService.getDailyForecast(query, 7)
                emit(DailyForecastMapper.mapDailyForecastToDomain(dailyForecastData))
                mCache.insert(DataByQuery(query, dailyForecastData, System.currentTimeMillis()))
                interactor.error(null)
            } catch (e: Exception) {
                e.printStackTrace()
                interactor.error(e)
            } finally {
                interactor.loading(false)
            }
        }
    }
}