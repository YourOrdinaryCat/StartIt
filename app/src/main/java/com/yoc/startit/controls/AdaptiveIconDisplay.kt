package com.yoc.startit.controls

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter

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