package com.github.extmkv.weather.feature.settings

import android.app.Activity
import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.view.View
import com.github.extmkv.weather.R
import com.github.extmkv.weather.util.PreferenceManager.Companion.PREF_UNITS


class SettingsFragment : PreferenceFragmentCompat() {

    companion object {
        /** SettingsFragment Request Code*/
        const val REQUEST_CODE = 1001
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().setTitle(R.string.settings)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        // Verify if the user changes the units.
        (findPreference(PREF_UNITS) as ListPreference)
                .onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, _ ->
            activity?.setResult(Activity.RESULT_OK)
            true
        }
    }


}