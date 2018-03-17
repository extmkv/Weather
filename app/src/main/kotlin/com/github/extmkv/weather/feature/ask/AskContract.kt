package com.github.extmkv.weather.feature.ask

import com.github.extmkv.weather.base.mvp.BasePresenter
import com.github.extmkv.weather.base.mvp.BaseView

class AskContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View>
}