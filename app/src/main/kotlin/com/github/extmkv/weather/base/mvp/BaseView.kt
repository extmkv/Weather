package com.github.extmkv.weather.base.mvp

import retrofit2.Call

/**
 * MVP BaseView
 */
interface BaseView {
    /**
     * Called when view are created.
     */
    fun onCreate()

    /**
     * Add the requests callback to an array to be cancel when the view stop.
     *
     * @param callback The request callback to be added.
     */
    fun addRequestCallback(callback: Call<*>)
}