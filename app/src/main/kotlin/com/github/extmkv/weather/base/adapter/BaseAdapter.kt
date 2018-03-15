package com.github.extmkv.weather.base.adapter

import android.support.v7.widget.RecyclerView
import com.github.extmkv.weather.model.Entry

abstract class BaseAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    protected var items: ArrayList<Entry> = arrayListOf()

    override fun getItemCount(): Int = items.size

    fun addAll(itemsToBeAdded: List<Entry>) {
        items.addAll(itemsToBeAdded)
        notifyDataSetChanged()
    }
}