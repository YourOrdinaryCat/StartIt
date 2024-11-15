package com.yoc.startit.models

import android.graphics.drawable.Drawable

class DisplayApp {
    var label: String? = null
    var packageName: String? = null

    // Icon resources
    var icon: Drawable? = null

    override fun equals(other: Any?): Boolean {
        if (other is DisplayApp) {
            return packageName.equals(other.packageName)
        } else if (other is String) {
            return packageName.equals(other)
        }

        return false
    }

    override fun hashCode(): Int {
        return packageName?.hashCode() ?: 0
    }
}