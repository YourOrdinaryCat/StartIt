package com.yoc.startit.helpers

import android.content.Context

object Launcher {
    /**
     * Launches the package with the specified name. Returns true if
     * package was found, false otherwise.
     */
    fun launch(packageName: String, context: Context): Boolean {
        val manager = context.packageManager
        val launchIntent = manager.getLaunchIntentForPackage(packageName)

        if (launchIntent != null) {
            context.startActivity(launchIntent)
        }

        return launchIntent != null
    }
}