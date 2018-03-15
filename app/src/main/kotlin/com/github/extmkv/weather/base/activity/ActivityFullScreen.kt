package com.github.extmkv.weather.base.activity

import android.support.v4.app.Fragment
import com.github.extmkv.weather.R
import com.github.extmkv.weather.feature.splash.FragmentSplash
import com.massivedisaster.afm.activity.BaseActivity

class ActivityFullScreen : BaseActivity() {

    override fun layoutToInflate(): Int = R.layout.activity_fullscreen

    override fun getContainerViewId(): Int = R.id.frmContainer

    override fun getDefaultFragment(): Class<out Fragment>? = FragmentSplash::class.java
}