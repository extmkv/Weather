package com.github.extmkv.weather.base.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.mvp.FragmentMVP
import kotlinx.android.synthetic.main.fragment_state.*
import kotlinx.android.synthetic.main.param_state_error.*
import kotlinx.android.synthetic.main.param_state_loading.*

abstract class LoadingFragment<T : LoadingContract.Presenter<*>> : FragmentMVP<T>(), LoadingContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_state, container, false)

        if (layoutToInflate() != -1) {
            inflater.inflate(layoutToInflate(), view.findViewById(R.id.frmStateContent), true)
        }

        restoreInstanceState(savedInstanceState)

        arguments?.let {
            getFromBundle(it)
        }

        doOnCreated()

        return view
    }

    override fun showLoading() {
        rtlLoading.start()
        lnrError.visibility = View.GONE
        frmStateContent.visibility = View.GONE
    }

    override fun showContent() {
        rtlLoading.stop()
        lnrError.visibility = View.GONE
        frmStateContent.visibility = View.VISIBLE
    }

    override fun showError(error: String, method: (() -> Unit)?) {
        lnrError.visibility = View.VISIBLE
        frmStateContent.visibility = View.GONE
        rtlLoading.stop()

        txtError.text = error
        method?.let {
            btnRetry.setOnClickListener { method.invoke() }
        }
    }
}