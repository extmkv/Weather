package com.github.extmkv.weather.network

import com.github.extmkv.weather.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitAdapter {

    companion object {
        const val APP_ID = "appid"
    }

    val adapter: IRequests
        get() = retrofit.create<IRequests>(IRequests::class.java)

    private val retrofit: Retrofit
        get() {
            val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create()

            return Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL).addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getOkHttpClient(BuildConfig.API_TIMEOUT))
                    .build()
        }

    /**
     * Create an OkHttpClient to be used by Retrofit.
     *
     * @param timeout The timeout value for connection and read.
     * @return The OkHttpClient.
     */
    private fun getOkHttpClient(timeout: Long): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .readTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .addInterceptor { chain ->
                    val originalHttpUrl = chain.request().url()

                    // Inject the app id query parameter in all requests.
                    val url = originalHttpUrl.newBuilder()
                            .addQueryParameter(APP_ID, BuildConfig.API_APPID)
                            .build()
                    val requestBuilder = chain.request().newBuilder()
                            .url(url)

                    chain.proceed(requestBuilder.build())
                }.build()
    }
}