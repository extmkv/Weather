package com.github.extmkv.weather.feature.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.adapter.BaseAdapter
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.util.extension.setHour

class ForecastAdapter : BaseAdapter<Entry, ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_forecast, parent, false)

        return ForecastViewHolder(v)
    }


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = items[position]

        holder.imgIcon.setImageResource(item.weather[0].icon.res)

        holder.txtWeather.text = item.weather[0].description
        holder.txtTemperature.text = String.format("%sº", item.main.temp)
        holder.txtTime.setHour(item.dt_txt)

        holder.itemView.setOnClickListener {
            onChildCLickListener?.onChildClick(it, items[position], position)
        }
    }

}