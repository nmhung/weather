package net.fitken.domain.usecases

import kotlinx.coroutines.CoroutineScope
import net.fitken.domain.common.Interactor
import net.fitken.domain.repository.WeatherRepository

class GetDailyForecastUseCase(private val mWeatherRepository: WeatherRepository) {
    suspend operator fun invoke(coroutineScope: CoroutineScope, interactor: Interactor, query: String) =
        mWeatherRepository.getDailyForecast(coroutineScope, interactor, query)
}