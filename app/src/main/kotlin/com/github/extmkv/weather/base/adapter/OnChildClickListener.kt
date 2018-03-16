package com.github.extmkv.weather.base.adapter

import android.view.View

/**
 * Listener called when an element is clicked.
 *
 * @param <T> The type of the elements.
 */
interface OnChildClickListener<in T> {
    /**
     * Method called when an element is clicked.
     *
     * @param view     The view clicked.
     * @param item        The element clicked.
     * @param position The position of the element clicked.
     */
    fun onChildClick(view: View, item: T, position: Int)
}