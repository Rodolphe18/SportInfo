package com.example.sportinfo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

/**
 * Light default theme color scheme
 */

val LightDefaultColorScheme = lightColorScheme(
    primary = Color.DarkGray,
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFFF3C00),
    onSecondary = Color(0xFFFFFFFF),
    tertiary = Color(0xFF367B69),
    onTertiary = Color(0xFFFFFFFF),
    background = Color(0xFFF8F9FB),
    onBackground = Color(0xFF393D41),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF272A30),
    onSurfaceVariant = Color.DarkGray,
    onSecondaryContainer = Color.Black,
    onTertiaryContainer = Color(0xFF5A5D64),
    tertiaryContainer = Color.LightGray,

    )

/**
 * Dark default theme color scheme
 */

val DarkDefaultColorScheme = darkColorScheme(
    primary = Color(0xFF0061EE),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFFF3C00),
    onSecondary = Color(0xFFFFFFFF),
    tertiary = Color(0xFF367B69),
    onTertiary = Color(0xFFFFFFFF),
    background = Color(0xFF272A30),
    onBackground = Color(0xFFE3E6EB),
    surface = Color(0xFF18191B),
    onSurface = Color(0xFFFFFFFF),
    onSurfaceVariant = Color.LightGray,
    onSecondaryContainer = Color.White,
    onTertiaryContainer = Color(0xFFAFB3B8),
    tertiaryContainer = Color.DarkGray
)


@Composable
fun SportInfoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colorScheme = if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme

    CompositionLocalProvider {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}
