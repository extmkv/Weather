package com.github.extmkv.weather

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initFabric(this)
    }

    private fun initFabric(context: Context) {
        val core = CrashlyticsCore.Builder().disabled(!BuildConfig.REPORT_CRASH).build()
        val kit = Crashlytics.Builder().core(core).build()
        Fabric.with(context, kit)
    }
}