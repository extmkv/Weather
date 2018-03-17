package com.github.extmkv.weather.feature.splash

import com.github.extmkv.weather.BuildConfig
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.activity.ActivityToolbar
import com.github.extmkv.weather.feature.home.HomeFragment
import com.massivedisaster.adal.fragment.AbstractSplashFragment
import com.massivedisaster.adal.fragment.AbstractSplashFragment.OnFinishSplashScreen
import com.massivedisaster.afm.ActivityCall

class SplashFragment : AbstractSplashFragment() {

    override fun layoutToInflate(): Int = R.layout.fragment_splash

    override fun getSplashTimeOut(): Long = BuildConfig.SPLASH_TIMEOUT

    /**
     * Called when shows the splash
     */
    override fun onSplashStarted() {
        onSplashFinish(openHome())
    }

    /**
     * Called after the splash time out.
     */
    private fun openHome(): OnFinishSplashScreen = OnFinishSplashScreen {
        ActivityCall.init(requireContext(), ActivityToolbar::class, HomeFragment::class)
                .build()

        requireActivity().finish()
    }
}