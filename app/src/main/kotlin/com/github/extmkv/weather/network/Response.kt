package com.github.extmkv.weather.network

import com.github.extmkv.weather.model.City
import com.massivedisaster.adal.network.APIResponse

class Response<out T>(val cod: String, val message: String, val list: T, val city: City) : APIResponse() {

    override fun getError(): String = message

    override fun getErrorCode(): Int = 0
}