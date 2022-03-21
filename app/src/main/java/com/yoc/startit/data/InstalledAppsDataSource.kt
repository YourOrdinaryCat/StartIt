package com.yoc.startit.data

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.BitmapDrawable
import com.yoc.startit.models.DisplayApp


object InstalledAppsDataSource {
    private var appList: MutableList<DisplayApp> = mutableListOf<DisplayApp>()
    val appsList: MutableList<DisplayApp>
        get() = appList

    fun populateAppList(context: Context) {
        val manager = context.packageManager

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        val allApps: List<ResolveInfo> =
            manager.queryIntentActivities(mainIntent, 0)

        for (ri in allApps) {
            val app = DisplayApp()
            app.packageName = ri.activityInfo.packageName

            if (!appsList.contains(app)) {
                app.label = ri.loadLabel(manager).toString()

                val drawable = ri.loadIcon(manager)
                if (drawable is BitmapDrawable) {
                    app.background = drawable
                } else if (drawable is AdaptiveIconDrawable) {
                    app.background = drawable.background
                    app.foreground = drawable.foreground
                }

                appsList.add(app)
            }
        }
    }
}