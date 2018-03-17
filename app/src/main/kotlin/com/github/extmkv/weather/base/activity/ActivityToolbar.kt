package com.github.extmkv.weather.base.activity

import android.support.v4.app.Fragment
import android.view.MenuItem
import com.github.extmkv.weather.R
import com.github.extmkv.weather.feature.splash.SplashFragment
import com.massivedisaster.afm.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_toolbar.*

class ActivityToolbar : BaseActivity() {

    override fun layoutToInflate(): Int = R.layout.activity_toolbar

    override fun getContainerViewId(): Int = R.id.frmContainer

    override fun getDefaultFragment(): Class<out Fragment>? = SplashFragment::class.java

    override fun doOnCreated() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}