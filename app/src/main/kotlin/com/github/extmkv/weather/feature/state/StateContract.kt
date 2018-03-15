package com.github.extmkv.weather.feature.state

import com.github.extmkv.weather.base.mvp.BasePresenter
import com.github.extmkv.weather.base.mvp.BaseView

class StateContract {

    interface View : BaseView {
        fun showContent()
        fun showLoading()
        fun showError(error: Int, method: (() -> Unit)? = null)
    }

    interface Presenter<T : View> : BasePresenter<T>
}