package com.github.extmkv.weather.feature.home

import android.content.Intent
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.activity.ActivityArcToolbar
import com.github.extmkv.weather.base.activity.ActivityToolbar
import com.github.extmkv.weather.feature.ask.AskDialog
import com.github.extmkv.weather.feature.forecast.ForecastFragment
import com.github.extmkv.weather.feature.home.location.LocationFragment
import com.github.extmkv.weather.feature.settings.SettingsFragment
import com.github.extmkv.weather.model.City
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.model.ResultQuery
import com.github.extmkv.weather.util.PreferenceManager
import com.github.extmkv.weather.util.extension.getLocaleString
import com.massivedisaster.afm.ActivityCall
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.param_state_error.*
import java.util.*


class HomeFragment : LocationFragment<HomeContract.Presenter>(), HomeContract.View {

    companion object {
        const val ARG_ASK = "ARG_ASK"
    }

    private val dialog by lazy { AskDialog() }

    override fun layoutToInflate() = R.layout.fragment_home

    override fun createPresenter(): HomeContract.Presenter = HomePresenterImpl(this)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnSettings -> {
                openSettings()
                return true
            }
        }

        return false
    }

    private fun openSettings() {
        ActivityCall.init(this, ActivityToolbar::class, SettingsFragment::class)
                .setRequestCode(SettingsFragment.REQUEST_CODE)
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        requireActivity().setTitle(R.string.app_name)
        setHasOptionsMenu(true)

        btnListen.setOnClickListener { openAsk() }
        btnRetry.setOnClickListener { openAsk() }

        if (requireActivity().intent.hasExtra(ARG_ASK)) {
            requireActivity().intent.removeExtra(ARG_ASK)
            openAsk()
        }

        showContent()
    }

    override fun onResume() {
        super.onResume()
        txtExamples.text = getLocaleString(PreferenceManager.getLanguage(requireContext()),
                R.string.voice_example)
    }

    override fun onPause() {
        super.onPause()

        if (dialog.isAdded)
            dialog.dismiss()
    }

    override fun onStart() {
        super.onStart()
        showContent()
    }

    private fun openAsk() {
        showContent()

        dialog.setTargetFragment(this, AskDialog.REQUEST_CODE)
        dialog.show(fragmentManager, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AskDialog.REQUEST_CODE && data != null) {
            presenter.processRequest(requireContext(),
                    PreferenceManager.getLanguage(requireContext()).language,
                    PreferenceManager.getUnits(requireContext()),
                    data.getSerializableExtra(AskDialog.RESULT_QUERY) as ResultQuery)
        }
    }

    override fun openForecast(city: City, items: ArrayList<Entry>, result: ResultQuery) {
        ActivityCall.init(this, ActivityArcToolbar::class, ForecastFragment::class)
                .setBundle(ForecastFragment.getBundle(city, items, result))
                .build()
    }

    override fun requestLocation(result: ResultQuery) {
        requestLocation()
    }

    override fun onLocationFound(location: Location, isLastKnowLocation: Boolean) {
        presenter.requestForecastByCoordinates(requireContext(),
                PreferenceManager.getLanguage(requireContext()).language,
                PreferenceManager.getUnits(requireContext()),
                location.latitude,
                location.longitude)
    }
}