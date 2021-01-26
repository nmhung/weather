package net.fitken.weather.screens.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import net.fitken.base.viewmodel.BaseViewModel
import net.fitken.domain.common.Interactor
import net.fitken.domain.usecases.GetDailyForecastUseCase
import net.fitken.weather.entities.DailyForecast
import net.fitken.weather.mappers.DailyForecastMapper

class DashboardViewModel @ViewModelInject constructor(
    private val mGetDailyForecastUseCase: GetDailyForecastUseCase
) : BaseViewModel() {

    private var mDailyForecast: LiveData<DailyForecast?>? = null
    private var mIsLoading = MutableLiveData(false)
    private var mError: MutableLiveData<Exception?> = MutableLiveData(null)
    private var mQuery = MutableLiveData<String>()

    private val mInteractor = object : Interactor {
        override fun loading(isLoading: Boolean) {
            mIsLoading.postValue(isLoading)
        }

        override fun error(exception: Exception?) {
            mError.postValue(exception)
        }
    }


    fun getWeathers(): LiveData<DailyForecast?> {
        if (mDailyForecast == null) {
            mDailyForecast = MutableLiveData()
            loadWeathers()
        }
        return mDailyForecast!!
    }

    fun isLoading() = mIsLoading

    fun getError() = mError

    fun search(query: String) {
        mQuery.postValue(query)
    }

    private fun loadWeathers() {
        mDailyForecast = Transformations.switchMap(mQuery) { it ->
            mIsLoading.postValue(true)
            val result = executeLiveDataTask {
                mGetDailyForecastUseCase.invoke(viewModelScope, mInteractor, it)
            }
            mIsLoading.postValue(false)
            Transformations.map(result) { dailyForecastEntity ->
                return@map DailyForecastMapper.mapDailyForecastToPresentation(dailyForecastEntity)
            }
        }
    }
}