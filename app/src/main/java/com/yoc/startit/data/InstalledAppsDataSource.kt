package com.yoc.startit.data

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import com.yoc.startit.BuildConfig
import com.yoc.startit.models.DisplayApp

object InstalledAppsDataSource {
    private var appList: MutableList<DisplayApp> = mutableListOf()
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

            if (
                !appsList.contains(app) &&
                app.packageName != BuildConfig.APPLICATION_ID
            ) {
                app.label = ri.loadLabel(manager).toString()
                app.icon = ri.loadIcon(manager)

                appsList.add(app)
            }
        }

        appsList.sortBy { app -> app.label }
    }
}