package com.github.extmkv.weather.feature.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.extmkv.weather.R


class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imgIcon: ImageView = itemView.findViewById(R.id.imgIcon)
    var txtWeather: TextView = itemView.findViewById(R.id.txtWeather)
    var txtTemperature: TextView = itemView.findViewById(R.id.txtTemperatures)
    var txtTime: TextView = itemView.findViewById(R.id.txtTime)

}