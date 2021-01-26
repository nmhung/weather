package net.fitken.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import net.fitken.data.db.WeatherLocalCache
import net.fitken.data.entities.DailyForecastData
import net.fitken.data.entities.DataByQuery
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@RunWith(AndroidJUnit4::class)
open class WeatherLocalCacheUnitTest : DatabaseUnitTest() {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    private lateinit var weatherLocalCache: WeatherLocalCache

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        weatherLocalCache = WeatherLocalCache(appDatabase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `Insert a daily weather forecast and retrieve the same data`(): Unit = runBlocking {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(DailyForecastData::class.java)
        val jsonStream = javaClass.classLoader?.getResourceAsStream("saigon.json")
        val jsonString = jsonStream?.bufferedReader().use { it?.readText() }
        jsonString?.let {
            val dailyForecastData = jsonAdapter.fromJson(it)
            dailyForecastData?.let { dailyForecast ->
                launch(Dispatchers.Main) {
                    val query = "Saigon"
                    val dataByQuery =
                        DataByQuery(query, dailyForecast, System.currentTimeMillis())
                    weatherLocalCache.insert(dataByQuery)
                    val cache = weatherLocalCache.getDataByQuery(query)
                    Assert.assertEquals(dataByQuery, cache)
                }
            }
        }
    }
}