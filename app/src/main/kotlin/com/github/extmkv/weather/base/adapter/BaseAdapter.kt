package com.github.extmkv.weather.base.adapter

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<T, U : RecyclerView.ViewHolder> : RecyclerView.Adapter<U>() {

    var onChildCLickListener: OnChildClickListener<T>? = null

    protected var items: ArrayList<T> = arrayListOf()

    /**
     * Get the items size.
     *
     * @return The number of items.
     */
    override fun getItemCount(): Int = items.size

    /**
     * Add all items to the adapter and then call the notifyDataSetChanged.
     *
     * @param itemsToBeAdded Items to be added to the adapter.
     */
    fun addAll(itemsToBeAdded: List<T>) {
        items.addAll(itemsToBeAdded)
        notifyDataSetChanged()
    }

    /**
     * Replace all items in the adapter and then call the notifyDataSetChanged.
     *
     * @param itemsToBeAdded Items to be added to the adapter.
     */
    fun replaceAll(itemsToBeAdded: List<T>) {
        items.clear()
        items.addAll(itemsToBeAdded)
        notifyDataSetChanged()
    }
}