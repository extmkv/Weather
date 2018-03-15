package com.github.extmkv.weather.feature.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.github.extmkv.weather.R


class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var txtName: TextView = itemView.findViewById(R.id.txtName)
}