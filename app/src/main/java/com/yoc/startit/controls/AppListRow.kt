package com.yoc.startit.controls

import android.graphics.drawable.AdaptiveIconDrawable
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
        if (app.icon is AdaptiveIconDrawable) {
            app.icon?.let {
                AdaptiveIconDisplay(it as AdaptiveIconDrawable, size = 48.dp)
            }
        } else {
            app.icon?.let {
                IconDisplay(it, size = 48.dp)
            }
        }

        Spacer(modifier = Modifier.width(12.dp))
        app.label?.let { Text(it) }
    }
}