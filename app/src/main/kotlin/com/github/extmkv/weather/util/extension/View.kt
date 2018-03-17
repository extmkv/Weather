package com.github.extmkv.weather.util.extension

import android.widget.TextView
import com.github.extmkv.weather.util.DateUtil
import java.text.NumberFormat
import java.util.*


fun TextView.setHour(date: Date) {
    text = DateUtil.hourFormatter.format(date)
}

fun TextView.setPercentage(value: Float) {
    val format = NumberFormat.getPercentInstance(Locale.getDefault())
    text = String.format("%s", format.format(value / 100.0F))
}

fun TextView.setPressure(value: Double) {
    text = String.format("%s hPa", value)
}

fun TextView.setWind(value: Double, metric: String) {
    text = String.format("%s %s", value, metric)
}