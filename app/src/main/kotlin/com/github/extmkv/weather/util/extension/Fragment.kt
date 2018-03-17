package com.github.extmkv.weather.util.extension

import android.content.res.Configuration
import android.support.v4.app.Fragment
import java.util.*


fun Fragment.getLocaleString(requestedLocale: Locale, resourceId: Int, vararg objects: Any): String {
    val config = Configuration(requireContext().resources.configuration)
    config.setLocale(requestedLocale)
    return requireContext().createConfigurationContext(config).getString(resourceId, *objects)
}