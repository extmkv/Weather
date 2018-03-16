package com.github.extmkv.weather.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.adapter.BaseAdapter
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.util.extension.setHour

class HomeAdapter : BaseAdapter<Entry, HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_home, parent, false)

        return HomeViewHolder(v)
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
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