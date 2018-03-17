package com.github.extmkv.weather.model

import java.io.Serializable

class Weather(val id: Int,
              val icon: Type,
              val main: String,
              val description: String) : Serializable