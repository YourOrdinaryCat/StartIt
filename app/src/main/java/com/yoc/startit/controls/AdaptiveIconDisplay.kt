package com.yoc.startit.controls

import android.graphics.*
import android.graphics.drawable.AdaptiveIconDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Dp

@Composable
fun AdaptiveIconDisplay(icon: AdaptiveIconDrawable, size: Dp) {
    val bitmap = Bitmap.createBitmap(
        icon.intrinsicWidth, icon.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)
    icon.setBounds(0, 0, canvas.width, canvas.height)
    icon.draw(canvas)

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = null,
        modifier = Modifier.requiredSize(size)
    )
}