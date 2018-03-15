package com.github.extmkv.weather.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.adapter.BaseAdapter

class HomeAdapter : BaseAdapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_home, parent, false)

        return HomeViewHolder(v)
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = items[position]

        holder.txtName.text = item.name
    }

}