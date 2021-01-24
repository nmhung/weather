package net.fitken.data.api

import net.fitken.data.entities.DailyForecastData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast/daily?units=metric")
    suspend fun getDailyForecast(
        @Query("q") query: String,
        @Query("cnt") numberOfDays: Int
    ): DailyForecastData

}