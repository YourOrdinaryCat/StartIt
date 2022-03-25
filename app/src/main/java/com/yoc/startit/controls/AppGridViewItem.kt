package com.yoc.startit.controls

import android.graphics.drawable.AdaptiveIconDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.yoc.startit.models.DisplayApp

@Composable
fun AppGridViewItem(app: DisplayApp) {
    Surface(
        Modifier
            .padding(56.dp, 32.dp)
    ) {
        if (app.icon is AdaptiveIconDrawable) {
            app.icon?.let {
                AdaptiveIconDisplay(it as AdaptiveIconDrawable, size = 56.dp)
            }
        } else {
            app.icon?.let {
                IconDisplay(it, size = 56.dp)
            }
        }
    }
}