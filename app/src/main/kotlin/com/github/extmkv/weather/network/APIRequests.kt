package com.github.extmkv.weather.network

import com.github.extmkv.weather.model.Entry
import com.massivedisaster.adal.network.APIRequestCallback
import retrofit2.Call


class APIRequests {

    companion object {

        private fun getAdapter(): IRequests {
            return RetrofitAdapter().adapter
        }

        fun getForecastByCoordinates(callObject: APIRequestCallback<Response<List<Entry>>>, language: String, units: String, latitude: Double, longitude: Double): Call<*> {
            val call = getAdapter().getForecastByCoordinates(language, units, latitude, longitude)
            call.enqueue(callObject)

            return call
        }

        fun getForecastByLocal(callObject: APIRequestCallback<Response<List<Entry>>>, language: String, units: String, local: String): Call<*> {
            val call = getAdapter().getForecastByLocal(language, units, local)
            call.enqueue(callObject)

            return call
        }
    }
}
