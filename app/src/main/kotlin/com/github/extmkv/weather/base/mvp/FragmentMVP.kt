package com.github.extmkv.weather.base.mvp

import android.os.Bundle
import android.view.View
import com.massivedisaster.adal.fragment.AbstractRequestFragment
import retrofit2.Call

abstract class FragmentMVP<T : BasePresenter<out BaseView>> : AbstractRequestFragment(), BaseView {

    protected lateinit var presenter: T

    protected abstract fun createPresenter(): T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onCreate()
    }

    override fun doOnCreated() {
        presenter = createPresenter()
    }

    override fun addRequestCallback(callback: Call<*>) {
        addRequest(callback)
    }
}