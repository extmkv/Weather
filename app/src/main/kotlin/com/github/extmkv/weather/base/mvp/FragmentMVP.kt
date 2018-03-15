package com.github.extmkv.weather.base.mvp

import com.massivedisaster.adal.fragment.AbstractRequestFragment
import retrofit2.Call

abstract class FragmentMVP<T : BasePresenter<out BaseView>> : AbstractRequestFragment(), BaseView {

    protected lateinit var presenter: T

    protected abstract fun createPresenter(): T

    override fun doOnCreated() {
        presenter = createPresenter()
    }

    override fun addRequest(callback: Call<*>) {
        addRequest(callback)
    }
}