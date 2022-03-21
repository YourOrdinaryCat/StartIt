package com.yoc.startit.data

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import com.yoc.startit.models.DisplayApp


object InstalledAppsDataSource {
    private var appList: MutableList<DisplayApp> = mutableListOf<DisplayApp>()
    val appsList: MutableList<DisplayApp>
        get() = appList

    fun populateAppList(context: Context) {
        val pManager: PackageManager = context.packageManager

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        val allApps: List<ResolveInfo> =
            pManager.queryIntentActivities(mainIntent, 0)

        for (ri in allApps) {
            val app = DisplayApp()
            app.label = ri.loadLabel(pManager).toString()
            app.packageName = ri.activityInfo.packageName

            app.background = ri.activityInfo.loadIcon(pManager)
            appsList.add(app)
        }
    }
}