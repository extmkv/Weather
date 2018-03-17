package com.github.extmkv.weather.feature.home

import android.content.Context
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.model.ResultQuery
import com.github.extmkv.weather.network.APIRequests
import com.github.extmkv.weather.network.Response
import com.massivedisaster.adal.network.APIError
import com.massivedisaster.adal.network.APIRequestCallback


class HomePresenterImpl(val view: HomeContract.View) : HomeContract.Presenter {

    lateinit var result: ResultQuery

    override fun processRequest(context: Context, units: String, result: ResultQuery) {
        this.result = result

        result.city?.let {
            requestForecastByLocal(context, units, it)
        } ?: view.requestLocation(result)
    }

    override fun requestForecastByLocal(context: Context, units: String, local: String) {
        view.showLoading()

        view.addRequestCallback(
                APIRequests.getForecastByLocal(object : APIRequestCallback<Response<List<Entry>>>(context) {
                    override fun onSuccess(response: Response<List<Entry>>) {
                        view.showContent()
                        view.openForecast(response.city,
                                response.list as ArrayList<Entry>,
                                result)
                    }

                    override fun onError(error: APIError, isServerError: Boolean) {
                        view.showError(error.message)
                    }
                }, units, local))
    }

    override fun requestForecastByCoordinates(context: Context, units: String, latitude: Double, longitude: Double) {
        view.showLoading()
        view.addRequestCallback(
                APIRequests.getForecastByCoordinates(object : APIRequestCallback<Response<List<Entry>>>(context) {
                    override fun onSuccess(response: Response<List<Entry>>) {
                        view.showContent()
                        view.openForecast(response.city,
                                response.list as ArrayList<Entry>,
                                result)
                    }

                    override fun onError(error: APIError, isServerError: Boolean) {
                        view.showError(error.message)

                    }
                }, units, latitude, longitude))
    }
}
