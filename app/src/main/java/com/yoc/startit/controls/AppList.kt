package com.yoc.startit.controls

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.yoc.startit.data.InstalledAppsDataSource

@Composable
fun AppList(context: Context) {
    InstalledAppsDataSource.populateAppList(context)
    LazyColumn {
        items(InstalledAppsDataSource.appsList) { app ->
            AppListRow(app)
        }
    }
}