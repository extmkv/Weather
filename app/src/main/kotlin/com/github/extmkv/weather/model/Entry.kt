package com.github.extmkv.weather.model

import java.io.Serializable
import java.util.*

class Entry(val weather: List<Weather>,
            val main: Main,
            val wind: Wind,
            val dt_txt: Date) : Serializable