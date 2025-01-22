package com.francotte.android.sportinfo.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ItemColor(
    val itemColor: Color = Color.Unspecified,
)

/**
 * A composition local for [ItemColor].
 */
val LocalItemColor = staticCompositionLocalOf { ItemColor() }