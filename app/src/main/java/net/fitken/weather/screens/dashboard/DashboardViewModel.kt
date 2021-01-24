package net.fitken.weather.screens.dashboard

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import net.fitken.base.viewmodel.BaseViewModel
import net.fitken.domain.common.Interactor
import net.fitken.domain.usecases.GetDailyForecastUseCase
import net.fitken.rose.Rose
import net.fitken.weather.entities.Weather
import net.fitken.weather.mappers.WeatherMapper

class DashboardViewModel @ViewModelInject constructor(
    @Assisted private val savedState: SavedStateHandle,
    private val mGetDailyForecastUseCase: GetDailyForecastUseCase
) : BaseViewModel() {

    private var mWeathers: LiveData<List<Weather>>? = null
    private var mIsLoading = MutableLiveData(false)
    private var mError: MutableLiveData<Exception?> = MutableLiveData(null)
    private var mQuery = MutableLiveData<String>()

    private val mInteractor = object : Interactor {
        override fun loading(isLoading: Boolean) {
            Rose.error(isLoading)
            mIsLoading.postValue(isLoading)
        }

        override fun error(exception: Exception) {
            mError.postValue(exception)
        }
    }


    fun getWeathers(): LiveData<List<Weather>> {
        if (mWeathers == null) {
            mWeathers = MutableLiveData()
            loadWeathers()
        }
        return mWeathers!!
    }

    fun isLoading() = mIsLoading

    fun getError() = mError

    fun refresh() {
        loadWeathers()
    }

    fun search(query: String) {
        mQuery.postValue(query)
    }

    private fun loadWeathers() {
        mWeathers = Transformations.switchMap(mQuery) { it ->
            val result = executeLiveDataTask {
                mGetDailyForecastUseCase.invoke(viewModelScope, mInteractor, it)
            }
            Transformations.map(result) { weatherEntities ->
                return@map weatherEntities.map {
                    WeatherMapper.mapWeatherToPresentation(it)
                }
            }
        }
    }
}