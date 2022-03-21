package com.yoc.startit.controls

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yoc.startit.data.InstalledAppsDataSource
import com.yoc.startit.helpers.Launcher

@Composable
fun AppList(context: Context) {
    LazyColumn {
        items(InstalledAppsDataSource.appsList) { app ->
            Surface(
                Modifier
                    .clickable {
                        app.packageName?.let { Launcher.launch(it, context) }
                    }
                    .fillMaxWidth()
                    .padding(12.dp, 8.dp)
            ) {
                AppListRow(app)
            }
        }
    }
}