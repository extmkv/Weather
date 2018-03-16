package com.github.extmkv.weather.feature.home

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.activity.ActivityToolbar
import com.github.extmkv.weather.base.adapter.OnChildClickListener
import com.github.extmkv.weather.base.fragment.LocationFragment
import com.github.extmkv.weather.feature.detail.DetailDialog
import com.github.extmkv.weather.feature.settings.SettingsFragment
import com.github.extmkv.weather.model.Entry
import com.github.extmkv.weather.network.Response
import com.github.extmkv.weather.util.PreferenceManager
import com.massivedisaster.afm.ActivityCall
import kotlinx.android.synthetic.main.fragment_home.*


class FragmentHome : LocationFragment<HomeContract.Presenter>(), HomeContract.View {

    private val adapter = HomeAdapter()

    override fun layoutToInflate(): Int = R.layout.fragment_home

    override fun createPresenter(): HomeContract.Presenter = HomePresenterImpl(this)

    override fun onCreate() {
        super.onCreate()
        setHasOptionsMenu(true)

        rclItems.isNestedScrollingEnabled = false
        rclItems.layoutManager = LinearLayoutManager(requireContext())
        rclItems.adapter = adapter

        adapter.onChildCLickListener = object : OnChildClickListener<Entry> {
            override fun onChildClick(view: View, item: Entry, position: Int) {
                openEntryDetail(item)
            }
        }

        requestLocation()
    }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SettingsFragment.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            requestLocation()
        }
    }

    override fun onLocationFound(location: Location, isLastKnowLocation: Boolean) {
        presenter.requestData(requireContext(),
                PreferenceManager.getUnits(requireContext()),
                location.latitude,
                location.longitude)
    }

    override fun populateData(response: Response<List<Entry>>) {
        adapter.replaceAll(response.list)
        requireActivity().title = response.city.name
        rclItems.scheduleLayoutAnimation()
    }

    private fun openEntryDetail(item: Entry) {
        val dialog = DetailDialog()
        dialog.arguments = DetailDialog.getBundle(item)

        dialog.show(fragmentManager, null)
    }
}