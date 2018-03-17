package com.github.extmkv.weather.feature.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.adapter.BaseAdapter
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.util.extension.setHour
import com.github.extmkv.weather.util.extension.setTemperature

class ForecastAdapter : BaseAdapter<Entry, ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_forecast, parent, false)

        val viewHolder = ForecastViewHolder(view)

        view.setOnClickListener {
            onChildCLickListener?.onChildClick(it, items[viewHolder.adapterPosition], viewHolder.adapterPosition)
        }

        return viewHolder
    }


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = items[position]

        with(item) {
            holder.imgIcon.setImageResource(weather[0].icon.res)

            holder.txtWeather.text = weather[0].description
            holder.txtTemperature.setTemperature(main.temp)
            holder.txtTime.setHour(dt_txt)

        }
    }

}