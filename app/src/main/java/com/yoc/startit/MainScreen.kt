package com.yoc.startit

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.yoc.startit.data.InstalledAppsDataSource
import com.yoc.startit.helpers.GestureNavContract
import com.yoc.startit.models.DisplayApp
import com.yoc.startit.ui.theme.StartItTheme

class MainScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    color = MaterialTheme.colors.background
                ) {
                    AppList(LocalContext.current)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        // If the intent's action is main, we need to handle gestures
        val isActionMain = Intent.ACTION_MAIN == intent.action
        if (isActionMain) {
            handleGestureContract(intent)
        }
    }
}

/**
 * Handles going back to the launcher from an app.
 */
private fun handleGestureContract(intent: Intent) {
    val gnc = GestureNavContract.fromIntent(intent)
    // gnc?.sendEndPosition(null, null)
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

@Composable
fun AdaptiveIconDisplay(background: Drawable, foreground: Drawable) {
    Box(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .size(48.dp)
    ) {
        Image(
            rememberDrawablePainter(background),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Image(
            rememberDrawablePainter(foreground),
            contentDescription = null,
            Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun IconDisplay(icon: Drawable) {
    Image(
        rememberDrawablePainter(icon),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .size(48.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StartItTheme {
        AppList(LocalContext.current)
    }
}