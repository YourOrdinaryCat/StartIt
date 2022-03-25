package com.yoc.startit.views

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.yoc.startit.controls.AppGridViewItem
import com.yoc.startit.data.InstalledAppsDataSource
import com.yoc.startit.helpers.Launcher

@Composable
fun AllAppsGridView(context: Context) {
    LazyRow {
        items(InstalledAppsDataSource.appsList) { app ->
            Surface(
                Modifier
                    .clickable {
                        app.packageName?.let { Launcher.launch(it, context) }
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .padding(12.dp, 0.dp),
                color = MaterialTheme.colors.background
            ) {
                AppGridViewItem(app)
            }
        }
    }
}