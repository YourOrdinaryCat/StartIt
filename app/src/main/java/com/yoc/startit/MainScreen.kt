package com.yoc.startit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.yoc.startit.controls.AppList
import com.yoc.startit.data.InstalledAppsDataSource
import com.yoc.startit.helpers.GestureNavContract
import com.yoc.startit.ui.theme.StartItTheme
import com.yoc.startit.views.AllAppsGridView

class MainScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return

        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        setContent {
            StartItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    InstalledAppsDataSource.populateAppList(LocalContext.current)

                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            "StartIt",
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.h1,
                            modifier = Modifier.padding(12.dp, 24.dp, 0.dp, 0.dp)
                        )
                        AllAppsGridView(LocalContext.current)
                    }

                    BackHandler {
                        // No need to do anything here
                    }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StartItTheme {
        AppList(LocalContext.current)
    }
}