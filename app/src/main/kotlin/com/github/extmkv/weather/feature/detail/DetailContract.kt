package com.github.extmkv.weather.feature.detail

import com.github.extmkv.weather.base.mvp.BasePresenter
import com.github.extmkv.weather.base.mvp.BaseView

class DetailContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View>
}