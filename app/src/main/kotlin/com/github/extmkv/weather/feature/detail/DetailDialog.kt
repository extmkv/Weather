package com.github.extmkv.weather.feature.detail

import android.os.Bundle
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.mvp.DialogMVP
import com.github.extmkv.weather.model.Entry
import kotlinx.android.synthetic.main.dialog_detail.*

class DetailDialog : DialogMVP<DetailContract.Presenter>(), DetailContract.View {

    companion object {
        private const val ARG_ENTRY = "ARG_ENTRY"

        /**
         * Create the bundle to pass to a DetailDialog.
         *
         * @param entry to be added to the bundle.
         * @return the bundle.
         */
        fun getBundle(entry: Entry): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(ARG_ENTRY, entry)
            return bundle
        }
    }

    override fun createPresenter(): DetailContract.Presenter = DetailPresenterImpl(this)

    override fun layoutToInflate(): Int = R.layout.dialog_detail

    override fun getFromBundle(bundle: Bundle) {
        entry = bundle.getSerializable(ARG_ENTRY) as Entry
    }

    private lateinit var entry: Entry

    override fun onCreate() {
        btnDismiss.setOnClickListener { dismiss() }

        imgWeather.setImageResource(entry.weather[0].icon.res)
        txtTitle.text = entry.weather[0].description

        txtHumidity.text = entry.main.humidity.toString()
        txtPressure.text = entry.main.pressure.toString()

        txtWind.text = entry.wind.speed.toString()
        txtSeaLevel.text = entry.main.sea_level.toString()
    }
}