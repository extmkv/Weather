package com.github.extmkv.weather.network

import com.github.extmkv.weather.model.Entry
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface IRequests {

    @POST("forecast")
    fun getForecastByCoordinates(
            @Query("lang") lang: String,
            @Query("units") units: String,
            @Query("lat") latitude: Double,
            @Query("lon") longitude: Double): Call<Response<List<Entry>>>

    @POST("forecast")
    fun getForecastByLocal(
            @Query("lang") lang: String,
            @Query("units") units: String,
            @Query("q") local: String): Call<Response<List<Entry>>>

}
