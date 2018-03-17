package com.github.extmkv.weather.feature.home

import android.content.Context
import com.github.extmkv.weather.base.state.StateContract
import com.github.extmkv.weather.model.City
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.model.ResultQuery

class HomeContract {

    interface View : StateContract.View {
        fun openForecast(city: City, items: ArrayList<Entry>, result: ResultQuery)
        fun requestLocation(result: ResultQuery)
    }

    interface Presenter : StateContract.Presenter<View> {
        fun processRequest(context: Context, units: String, result: ResultQuery)
        fun requestForecastByLocal(context: Context, units: String, local: String)
        fun requestForecastByCoordinates(context: Context, units: String, latitude: Double, longitude: Double)
    }
}