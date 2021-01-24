package net.fitken.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import net.fitken.data.api.WeatherService
import net.fitken.data.mappers.DailyForecastMapper
import net.fitken.domain.common.Interactor
import net.fitken.domain.entities.DailyForecastEntity
import net.fitken.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val mService: WeatherService) : WeatherRepository {
    override suspend fun getDailyForecast(
        coroutineScope: CoroutineScope,
        interactor: Interactor, query: String
    ): LiveData<DailyForecastEntity> = liveData {
        try {
            interactor.loading(true)
            val dailyForecastData = mService.getDailyForecast(query, 7)
            emit(DailyForecastMapper.mapDailyForecastToDomain(dailyForecastData))
            interactor.error(null)
        } catch (e: Exception) {
            e.printStackTrace()
            interactor.error(e)
        } finally {
            interactor.loading(false)
        }
    }
}