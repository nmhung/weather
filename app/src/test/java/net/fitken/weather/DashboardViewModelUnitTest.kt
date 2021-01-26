package net.fitken.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.fitken.domain.usecases.GetDailyForecastUseCase
import net.fitken.weather.screens.dashboard.DashboardViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
class DashboardViewModelUnitTest {
    @Mock
    private lateinit var viewModel: DashboardViewModel

    @Mock
    private lateinit var getDailyForecastUseCase: GetDailyForecastUseCase

    @Mock
    private lateinit var observer: Observer<Boolean>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(DashboardViewModel(getDailyForecastUseCase))
    }

    @Test
    fun `Verify data is loaded when perform search`() {
        viewModel.isLoading().observeForever(observer)
        verify(observer).onChanged(false)
        viewModel.search("")
        viewModel.getWeathers().observeForTesting {
            verify(observer).onChanged(true)
        }
    }

    /**
     * Observes a [LiveData] until the `block` is done executing.
     */
    private fun <T> LiveData<T>.observeForTesting(block: () -> Unit) {
        val observer = Observer<T> { }
        try {
            observeForever(observer)
            block()
        } finally {
            removeObserver(observer)
        }
    }
}