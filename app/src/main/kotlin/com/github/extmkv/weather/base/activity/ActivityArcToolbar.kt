package com.github.extmkv.weather.base.activity

import com.github.extmkv.weather.R
import com.massivedisaster.afm.activity.BaseActivity
import com.massivedisaster.widget.extensions.setAppBarLayout
import kotlinx.android.synthetic.main.activity_arc_toolbar.*

class ActivityArcToolbar : BaseActivity() {

    override fun layoutToInflate(): Int = R.layout.activity_arc_toolbar

    override fun getContainerViewId(): Int = R.id.frmContainer

    override fun doOnCreated() {
        setSupportActionBar(toolbar)
        arcToolbar.setAppBarLayout(appbar)
    }

    /**
     * Set the title to the CollapsingToolbarLayout.
     *
     * @param title The title to be setted.
     */
    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        clpToolbarLayout.title = title
    }
}