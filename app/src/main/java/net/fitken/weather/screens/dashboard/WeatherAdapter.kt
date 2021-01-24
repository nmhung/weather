package net.fitken.weather.screens.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import net.fitken.base.recyclerview.BaseRecyclerAdapter
import net.fitken.weather.R
import net.fitken.weather.databinding.ItemWeatherBinding
import net.fitken.weather.entities.Weather

class WeatherAdapter : BaseRecyclerAdapter<WeatherAdapter.WeatherViewHolder, Weather>() {

    class WeatherViewHolder(itemWeatherBinding: ItemWeatherBinding) :
        BaseRecyclerAdapter.BaseHolder<ItemWeatherBinding, Weather>(itemWeatherBinding) {
        override fun bindData(data: Weather) {
            super.bindData(data)
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