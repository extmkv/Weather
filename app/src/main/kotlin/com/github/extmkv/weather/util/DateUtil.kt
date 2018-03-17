package com.github.extmkv.weather.util

import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        var hourFormatter: Format = SimpleDateFormat("HH:mm a", Locale.getDefault())
    }
}