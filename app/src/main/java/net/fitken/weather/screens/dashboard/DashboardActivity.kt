package net.fitken.weather.screens.dashboard

import android.os.Bundle
import androidx.activity.viewModels
import com.jakewharton.rxbinding.widget.RxTextView
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.base.activity.BaseActivity
import net.fitken.base.recyclerview.VerticalLinearItemDecoration
import net.fitken.weather.R
import net.fitken.weather.databinding.ActivityDashboardBinding
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    private val mAdapter: WeatherAdapter by lazy { WeatherAdapter() }
    private val mViewModel: DashboardViewModel by viewModels()

    override fun getLayoutResource() = R.layout.activity_dashboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewDataBinding.rvWeathers.adapter = mAdapter

        mViewDataBinding.rvWeathers.addItemDecoration(
            VerticalLinearItemDecoration(
                resources.getDimension(R.dimen.positive_5dp).toInt(),
                resources.getDimension(R.dimen.positive_5dp).toInt(),
                resources.getDimension(R.dimen.positive_5dp).toInt(),
                resources.getDimension(R.dimen.positive_5dp).toInt(),
            )
        )

        RxTextView.textChanges(mViewDataBinding.etSearch)
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter {
                return@filter it.length >= 3
            }
            .map {
                return@map it.toString()
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mViewModel.search(it)
            }, {
                it.printStackTrace()
            })
    }

    override fun onStart() {
        super.onStart()
        mViewModel.getWeathers().observe(this, {
            it?.let { dailyForecast ->
                mAdapter.update(dailyForecast.result)
                mViewDataBinding.city = dailyForecast.city.name
            }
            mViewDataBinding.isHaveData = it != null
        })
        mViewModel.getError().observe(this, {
            showError(it)
        })
        mViewModel.isLoading().observe(this, {
            mViewDataBinding.isLoading = it
        })
    }
}