package com.github.extmkv.weather.feature.forecast

import com.github.extmkv.weather.base.mvp.BasePresenter
import com.github.extmkv.weather.base.mvp.BaseView

class ForecastContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View>
}