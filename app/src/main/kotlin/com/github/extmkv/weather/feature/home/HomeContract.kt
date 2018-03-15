package com.github.extmkv.weather.feature.home

import com.github.extmkv.weather.feature.state.StateContract
import com.github.extmkv.weather.model.Entry

class HomeContract {

    interface View : StateContract.View {
        fun populateData(items: ArrayList<Entry>)
    }

    interface Presenter : StateContract.Presenter<View> {
        fun requestData()
    }
}