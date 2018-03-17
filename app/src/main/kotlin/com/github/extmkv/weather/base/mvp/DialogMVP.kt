package com.github.extmkv.weather.base.mvp

import android.os.Bundle
import android.view.View
import com.massivedisaster.adal.dialogs.AbstractRequestDialogFragment
import retrofit2.Call

abstract class DialogMVP<T : BasePresenter<out BaseView>> : AbstractRequestDialogFragment(), BaseView {

    protected lateinit var presenter: T

    protected abstract fun createPresenter(): T

    override fun doOnCreated() {
        //Initialize Presenter
        presenter = createPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onCreate()
    }

    /**
     * Add the requests callback to an array to be cancel when the view stop.
     *
     * @param callback The request callback to be added.
     */
    override fun addRequestCallback(callback: Call<*>) {
        addRequest(callback)
    }
}