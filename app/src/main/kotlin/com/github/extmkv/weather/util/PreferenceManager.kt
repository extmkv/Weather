package com.github.extmkv.weather.util

import android.content.Context
import android.support.v7.preference.PreferenceManager

/**
 * Used to help to get values from shared preferences.
 */
class PreferenceManager {

    companion object {

        const val PREF_UNITS = "PREF_UNITS"

        /**
         * Get the units format from shared preference.
         *
         * @param context The context of the preferences whose values are wanted.
         */
        fun getUnits(context: Context): String {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
            return sharedPref.getString(PREF_UNITS, "kelvin")
        }
    }
}