package com.yoc.startit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoc.startit.controls.AppList
import com.yoc.startit.helpers.GestureNavContract
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StartItTheme {
        AppList(LocalContext.current)
    }
}