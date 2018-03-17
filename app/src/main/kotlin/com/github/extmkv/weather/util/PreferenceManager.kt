package com.github.extmkv.weather.util

import android.content.Context
import android.support.v7.preference.PreferenceManager
import java.util.*


/**
 * Used to help to get values from shared preferences.
 */
class PreferenceManager {

    companion object {

        const val PREF_UNITS = "PREF_UNITS"
        const val PREF_LANGUAGE = "PREF_LANGUAGE"

        /**
         * Get the units format from shared preference.
         *
         * @param context The context of the preferences whose values are wanted.
         */
        fun getUnits(context: Context): String {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
            return sharedPref.getString(PREF_UNITS, "kelvin")
        }

        /**
         * Get the language from shared preference.
         *
         * @param context The context of the preferences whose values are wanted.
         */
        fun getLanguage(context: Context): Locale {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

            val lang = sharedPref.getString(PREF_LANGUAGE, "en")
            val locale = Locale.getAvailableLocales().find {
                it.toString() == lang
            }

            return locale ?: Locale.US
        }
    }
}