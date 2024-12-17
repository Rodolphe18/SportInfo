package com.example.sportinfo.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun SportInfoGradientBackground(
    modifier: Modifier = Modifier,
    gradientColors: GradientColors = LocalGradientColors.current,
    content: @Composable () -> Unit,
) {
    val currentTopColor by rememberUpdatedState(gradientColors.top)
    val currentBottomColor by rememberUpdatedState(gradientColors.bottom)
    Box(
        modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(currentTopColor, currentBottomColor))))
    {
        content()
    }
}

@Immutable
data class GradientColors(
    val top: Color= Color.Transparent,
    val bottom:Color = Color.Transparent
)

/**
 * A composition local for [GradientColors].
 */
val LocalGradientColors = staticCompositionLocalOf { GradientColors() }