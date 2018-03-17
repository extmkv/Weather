package com.github.extmkv.weather.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.activity.ActivityToolbar
import com.github.extmkv.weather.feature.home.HomeFragment


class ForecaskWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        val count = appWidgetIds.size

        for (i in 0 until count) {
            val widgetId = appWidgetIds[i]
            val remoteViews = RemoteViews(context.packageName,
                    R.layout.widget_forecask)

            val configIntent = Intent(context, ActivityToolbar::class.java)
            configIntent.putExtra(HomeFragment.ARG_ASK, true)
            val configPendingIntent = PendingIntent.getActivity(context, 1000, configIntent, 0)
            remoteViews.setOnClickPendingIntent(R.id.btnListen, configPendingIntent)
            appWidgetManager.updateAppWidget(widgetId, remoteViews)
        }
    }
}