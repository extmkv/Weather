package com.github.extmkv.weather.feature.home

import android.support.v7.widget.LinearLayoutManager
import com.github.extmkv.weather.R
import com.github.extmkv.weather.feature.state.FragmentState
import com.github.extmkv.weather.model.Entry
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : FragmentState<HomeContract.Presenter>(), HomeContract.View {

    private val adapter = HomeAdapter()

    override fun layoutToInflate(): Int = R.layout.fragment_home

    override fun createPresenter(): HomeContract.Presenter = HomePresenterImpl(this)

    override fun onCreate() {
        rclItems.layoutManager = LinearLayoutManager(requireContext())
        rclItems.adapter = adapter

        presenter.requestData()
    }

    override fun populateData(items: ArrayList<Entry>) {
        adapter.addAll(items)
    }
}