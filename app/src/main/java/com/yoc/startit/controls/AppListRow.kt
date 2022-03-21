package com.yoc.startit.controls

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.yoc.startit.models.DisplayApp

@Composable
fun AppListRow(app: DisplayApp) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (app.hasAdaptiveIcon) {
            app.background?.let {
                AdaptiveIconDisplay(
                    background = it,
                    foreground = app.foreground!!
                )
            }
        } else {
            app.background?.let {
                IconDisplay(it)
            }
        }

        Column {
            app.label?.let { Text(it) }
            app.packageName?.let { Text(it) }
        }
    }
}