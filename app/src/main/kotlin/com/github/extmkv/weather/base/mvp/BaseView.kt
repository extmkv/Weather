package com.github.extmkv.weather.base.mvp

import retrofit2.Call

interface BaseView {
    fun onCreate()
    fun addRequest(callback: Call<*>)
}