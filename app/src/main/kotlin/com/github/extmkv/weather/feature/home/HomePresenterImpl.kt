package com.github.extmkv.weather.feature.home

class HomePresenterImpl(val view: HomeContract.View) : HomeContract.Presenter {

    override fun requestData() {
        view.showError(1)
        //view.showContent()
    }
}