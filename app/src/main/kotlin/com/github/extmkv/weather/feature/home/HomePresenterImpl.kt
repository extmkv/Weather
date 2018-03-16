package com.github.extmkv.weather.feature.home

import android.content.Context
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.network.APIRequests
import com.github.extmkv.weather.network.Response
import com.massivedisaster.adal.network.APIError
import com.massivedisaster.adal.network.APIRequestCallback

class HomePresenterImpl(val view: HomeContract.View) : HomeContract.Presenter {

    override fun requestData(context: Context, units: String, latitude: Double, longitude: Double) {
        view.showLoading()

        view.addRequestCallback(
                APIRequests.getForecast(object : APIRequestCallback<Response<List<Entry>>>(context) {
                    override fun onSuccess(response: Response<List<Entry>>) {
                        view.showContent()
                        view.populateData(response)
                    }

                    override fun onError(error: APIError, isServerError: Boolean) {
                        view.showError(error.message, {
                            requestData(context, units, latitude, longitude)
                        })
                    }
                }, units, latitude, longitude))
    }
}