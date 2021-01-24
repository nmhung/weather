package net.fitken.domain.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import net.fitken.domain.common.Interactor
import net.fitken.domain.entities.WeatherEntity

interface WeatherRepository {
    suspend fun getDailyForecast(
        coroutineScope: CoroutineScope,
        interactor: Interactor, query: String
    ): LiveData<List<WeatherEntity>>
}