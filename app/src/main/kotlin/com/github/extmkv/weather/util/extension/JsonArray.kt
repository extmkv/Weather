package com.github.extmkv.weather.util.extension

import com.google.gson.JsonArray


fun JsonArray.toStringArrayList(): ArrayList<String> {

    val arr = arrayListOf<String>()
    this.forEach {
        arr.add(it.asString)
    }

    return arr
}
