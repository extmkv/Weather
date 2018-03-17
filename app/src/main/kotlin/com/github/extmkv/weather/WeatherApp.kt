package com.github.extmkv.weather

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initFabric(this, !BuildConfig.REPORT_CRASH)
    }

    /**
     * Initialize the Fabric SDK with Crashlytics Kit.
     *
     * @param context The context of the preferences whose values are wanted.
     * @param disabled The flag to turn on/off the Crashlytics Kit.
     */
    private fun initFabric(context: Context, disabled: Boolean) {
        val core = CrashlyticsCore.Builder().disabled(disabled).build()
        val kit = Crashlytics.Builder().core(core).build()
        Fabric.with(context, kit)
    }
}