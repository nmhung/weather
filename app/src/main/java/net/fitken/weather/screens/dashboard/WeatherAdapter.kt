package net.fitken.weather.screens.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import net.fitken.base.recyclerview.BaseRecyclerAdapter
import net.fitken.weather.R
import net.fitken.weather.databinding.ItemWeatherBinding
import net.fitken.weather.entities.WeatherOfDay
import net.fitken.weather.utils.formatDate

class WeatherAdapter : BaseRecyclerAdapter<WeatherAdapter.WeatherViewHolder, WeatherOfDay>() {

    class WeatherViewHolder(itemWeatherBinding: ItemWeatherBinding) :
        BaseRecyclerAdapter.BaseHolder<ItemWeatherBinding, WeatherOfDay>(itemWeatherBinding) {
        override fun bindData(data: WeatherOfDay) {
            super.bindData(data)
            mViewDataBinding.tvDescription.text = data.weather.firstOrNull()?.main
            mViewDataBinding.tvDate.text = data.dt.formatDate()
            mViewDataBinding.tvAvg.text =
                String.format(itemView.context.getString(R.string.average), data.temp.getAverage())
            mViewDataBinding.tvPressure.text =
                String.format(itemView.context.getString(R.string.pressure), data.pressure)
            mViewDataBinding.tvHumidity.text =
                String.format(itemView.context.getString(R.string.humidity), data.humidity)
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_weather,
                parent,
                false
            )
        )
    }
}