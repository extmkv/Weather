package com.github.extmkv.weather.base.state

import com.github.extmkv.weather.base.mvp.BasePresenter
import com.github.extmkv.weather.base.mvp.BaseView

class StateContract {

    interface View : BaseView {

        /**
         * Show the content view.
         */
        fun showContent()

        /**
         * Show the loading view.
         */
        fun showLoading()

        /**
         * Show the error view and add a action to the button "try again".
         *
         * @param error Error to be showed.
         * @param method Method to be called when user perform the click on the "try again" button.
         */
        fun showError(error: String, method: (() -> Unit)? = null)
    }

    interface Presenter<T : View> : BasePresenter<T>
}