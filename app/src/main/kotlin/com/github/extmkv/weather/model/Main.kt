package com.github.extmkv.weather.model

import java.io.Serializable

class Main(val temp: Double,
           val pressure: Double,
           val sea_level: Double,
           val humidity: Float) : Serializable