package com.yoc.startit

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.yoc.startit.data.InstalledAppsDataSource
import com.yoc.startit.models.DisplayApp
import com.yoc.startit.ui.theme.StartItTheme

class MainScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppList(LocalContext.current)
                }
            }
        }
    }
}

@Composable
fun AppList(context: Context) {
    InstalledAppsDataSource.populateAppList(context)
    LazyColumn {
        items(InstalledAppsDataSource.appsList) { app ->
            AppRow(app)
        }
    }
}

@Composable
fun AppRow(app: DisplayApp) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box {
            app.background?.let {
                Image(
                    rememberDrawablePainter(it),
                    null
                )
            }

            app.foreground?.let {
                Image(
                    rememberDrawablePainter(it),
                    null
                )
            }
        }

        Column {
            app.label?.let { Text(it) }
            app.packageName?.let { Text(it) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StartItTheme {
        AppList(LocalContext.current)
    }
}