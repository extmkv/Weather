package com.github.extmkv.weather.feature.forecast

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.adapter.OnChildClickListener
import com.github.extmkv.weather.base.mvp.FragmentMVP
import com.github.extmkv.weather.feature.detail.DetailDialog
import com.github.extmkv.weather.model.City
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.model.ResultQuery
import com.github.extmkv.weather.util.DateUtil
import com.github.extmkv.weather.util.PreferenceManager
import com.github.extmkv.weather.util.extension.getLocaleString
import kotlinx.android.synthetic.main.fragment_forecast.*
import java.util.*


class ForecastFragment : FragmentMVP<ForecastContract.Presenter>(), ForecastContract.View {

    private lateinit var textToSpeech: TextToSpeech

    companion object {

        private const val ARG_CITY = "ARG_CITY"
        private const val ARG_FORECAST = "ARG_FORECAST"
        private const val ARG_RESULT_QUERY = "ARG_RESULT_QUERY"

        fun getBundle(city: City, forecast: ArrayList<Entry>, result: ResultQuery): Bundle {
            val bundle = Bundle()

            bundle.putSerializable(ARG_CITY, city)
            bundle.putSerializable(ARG_FORECAST, forecast)
            bundle.putSerializable(ARG_RESULT_QUERY, result)

            return bundle
        }
    }

    private val adapter by lazy { ForecastAdapter() }

    override fun layoutToInflate(): Int = R.layout.fragment_forecast

    override fun createPresenter(): ForecastContract.Presenter = ForecastPresenterImpl(this)

    private lateinit var resultQuery: ResultQuery
    private lateinit var lstEntries: List<Entry>
    private lateinit var city: City

    override fun getFromBundle(bundle: Bundle) {
        lstEntries = bundle.get(ARG_FORECAST) as List<Entry>
        resultQuery = bundle.get(ARG_RESULT_QUERY) as ResultQuery
        city = bundle.get(ARG_CITY) as City
    }

    override fun onCreate() {
        adapter.addAll(lstEntries)
        requireActivity().title = city.name

        rclItems.isNestedScrollingEnabled = false
        rclItems.layoutManager = LinearLayoutManager(requireContext())
        rclItems.adapter = adapter

        adapter.onChildCLickListener = object : OnChildClickListener<Entry> {
            override fun onChildClick(view: View, item: Entry, position: Int) {
                openEntryDetail(item)
            }
        }

        textToSpeech = TextToSpeech(requireContext(), TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech.setLanguage(PreferenceManager.getLanguage(requireContext()))
                if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                    speak()
                }
            }
        })
    }

    @Suppress("DEPRECATION")
    private fun speak() {
        val entry = lstEntries[0]

        textToSpeech.speak(getLocaleString(
                PreferenceManager.getLanguage(requireContext()),
                R.string.weather_message,
                entry.weather[0].description, city.name,
                DateUtil.hourFormatter.format(entry.dt_txt)),
                TextToSpeech.QUEUE_ADD, null)

        var query = ""
        if (resultQuery.hasAskedTemperature()) {
            query += getLocaleString(
                    PreferenceManager.getLanguage(requireContext()),
                    R.string.the_temperature_is,
                    entry.main.temp.toInt())
        }

        if (resultQuery.hasAskedWind()) {
            query += getLocaleString(
                    PreferenceManager.getLanguage(requireContext()),
                    R.string.the_wind_is,
                    entry.wind.speed.toInt())
        }

        if (resultQuery.hasAskedPrecipitation()) {
            query += getLocaleString(
                    PreferenceManager.getLanguage(requireContext()),
                    R.string.the_precipitation_not_available)
        }

        textToSpeech.speak(query, TextToSpeech.QUEUE_ADD, null)
    }

    private fun openEntryDetail(item: Entry) {
        val dialog = DetailDialog()
        dialog.arguments = DetailDialog.getBundle(item)

        dialog.show(fragmentManager, null)
    }

    override fun onPause() {
        super.onPause()

        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}