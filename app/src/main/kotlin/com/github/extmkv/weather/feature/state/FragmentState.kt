package com.github.extmkv.weather.feature.state

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.mvp.FragmentMVP
import kotlinx.android.synthetic.main.fragment_state.*

abstract class FragmentState<T : StateContract.Presenter<*>> : FragmentMVP<T>(), StateContract.View {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onCreate()
    }

    override fun showLoading() {
        rtlLoading.start()
        lnrError.visibility = View.GONE
        frmStateContent.visibility = View.GONE
    }

    override fun showContent() {
        frmStateContent.visibility = View.VISIBLE
        rtlLoading.stop()
        lnrError.visibility = View.GONE
    }

    override fun showError(error: Int, method: (() -> Unit)?) {
        lnrError.visibility = View.VISIBLE
        frmStateContent.visibility = View.GONE
        rtlLoading.stop()
    }
}