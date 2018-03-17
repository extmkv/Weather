package com.github.extmkv.weather.model

import android.support.annotation.DrawableRes
import com.github.extmkv.weather.R
import com.google.gson.annotations.SerializedName

enum class Type(@DrawableRes val res: Int) {

    @SerializedName("01d", alternate = ["01n"])
    CLEAR_SKY(R.drawable.ic_clear_sky),

    @SerializedName("02d", alternate = ["02n"])
    FEW_CLOUDS(R.drawable.ic_few_clouds),

    @SerializedName("03d", alternate = ["03n"])
    SCATTERED_CLOUDS(R.drawable.ic_scattered_clouds),

    @SerializedName("04d", alternate = ["04n"])
    BROKEN_CLOUDS(R.drawable.ic_scattered_clouds),

    @SerializedName("09d", alternate = ["09n"])
    SHOWER_RAIN(R.drawable.ic_shower_rain),

    @SerializedName("10d", alternate = ["10n"])
    RAIN(R.drawable.ic_rain),

    @SerializedName("11d", alternate = ["11n"])
    THUNDERSTORM(R.drawable.ic_thunderstorm),

    @SerializedName("13d", alternate = ["13n"])
    SNOW(R.drawable.ic_snow),

    @SerializedName("50d", alternate = ["50n"])
    MIST(R.drawable.ic_mist)
}