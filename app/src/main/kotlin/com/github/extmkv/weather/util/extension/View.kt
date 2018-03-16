package com.github.extmkv.weather.util.extension

import android.widget.TextView
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

private var hourFormatter: Format = SimpleDateFormat("HH:mm", Locale.getDefault())

fun TextView.setHour(date: Date) {
    text = hourFormatter.format(date)
}