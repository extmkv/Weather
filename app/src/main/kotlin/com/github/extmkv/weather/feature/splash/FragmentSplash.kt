package com.github.extmkv.weather.feature.splash

import com.github.extmkv.weather.BuildConfig
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.activity.ActivityArcToolbar
import com.github.extmkv.weather.feature.home.FragmentHome
import com.massivedisaster.adal.fragment.AbstractSplashFragment
import com.massivedisaster.adal.fragment.AbstractSplashFragment.OnFinishSplashScreen
import com.massivedisaster.afm.ActivityCall

class FragmentSplash : AbstractSplashFragment() {

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
    private fun openHome(): OnFinishSplashScreen {
        return OnFinishSplashScreen {
            ActivityCall.init(requireContext(), ActivityArcToolbar::class, FragmentHome::class)
                    .build()

            requireActivity().finish()
        }
    }

}