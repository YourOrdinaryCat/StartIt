package com.yoc.startit.controls

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

        Spacer(modifier = Modifier.width(12.dp))
        app.label?.let { Text(it) }
    }
}