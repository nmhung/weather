package net.fitken.weather.screens.dashboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.base.activity.BaseActivity
import net.fitken.base.recyclerview.VerticalLinearItemDecoration
import net.fitken.weather.R
import net.fitken.weather.databinding.ActivityDashboardBinding

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

        mViewModel.search("saigon")

    }

    override fun onStart() {
        super.onStart()
        mViewModel.getWeathers().observe(this, Observer {
            mAdapter.update(it)
        })
        mViewModel.getError().observe(this, Observer {
            showError(it)
        })
    }
}