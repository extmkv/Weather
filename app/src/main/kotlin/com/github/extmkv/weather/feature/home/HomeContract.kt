package com.github.extmkv.weather.feature.home

import android.content.Context
import com.github.extmkv.weather.base.state.StateContract
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.network.Response

class HomeContract {

    interface View : StateContract.View {
        fun populateData(response: Response<List<Entry>>)
    }

    interface Presenter : StateContract.Presenter<View> {
        fun requestData(context: Context, units: String, latitude: Double, longitude: Double)
    }
}