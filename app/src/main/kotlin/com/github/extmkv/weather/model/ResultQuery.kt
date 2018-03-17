package com.github.extmkv.weather.model

import ai.api.model.Result
import com.github.extmkv.weather.util.extension.toStringArrayList
import java.io.Serializable
import java.util.*

class ResultQuery(var city: String? = null,
                  var date: Date? = null,
                  private var types: ArrayList<String> = arrayListOf()) : Serializable {

    companion object {
        private const val CITY = "geo-city"
        private const val DATE = "time"
        private const val TYPES = "Type"

        private const val TEMPERATURE = "temperature"
        private const val WIND = "wind"
        private const val PRECIPITATION = "precipitation"
    }


    fun hasAskedTemperature(): Boolean = types.contains(TEMPERATURE)

    fun hasAskedWind(): Boolean = types.contains(WIND)

    fun hasAskedPrecipitation(): Boolean = types.contains(PRECIPITATION)

    fun setValues(queryResult: Result) {
        if (queryResult.parameters.containsKey(ResultQuery.CITY)) {
            city = queryResult.getStringParameter(ResultQuery.CITY)
        }

        if (queryResult.parameters.containsKey(ResultQuery.DATE)) {
            date = queryResult.getTimeParameter(ResultQuery.DATE)
        }

        if (queryResult.parameters.containsKey(ResultQuery.TYPES) && queryResult.parameters[ResultQuery.TYPES]!!.asJsonArray.size() > 0) {
            types = queryResult.parameters[ResultQuery.TYPES]!!.asJsonArray.toStringArrayList()
        }
    }
}