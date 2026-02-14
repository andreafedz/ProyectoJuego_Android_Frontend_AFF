package com.aff.proyectojuegoandreaff.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBFA9FF),          // color morado
    onPrimary = Color(0xFF1B102A),

    secondary = Color(0xFF7EE0FF),        // tonos frios
    onSecondary = Color(0xFF07131A),

    background = Color(0xFF0B0814),       // fondo noche
    onBackground = Color(0xFFEDE7FF),

    surface = Color(0xFF151022),          // tarjetas oscuras
    onSurface = Color(0xFFEDE7FF),

    surfaceVariant = Color(0xFF20163A),   // panel “Resultado”
    onSurfaceVariant = Color(0xFFDCD2FF),

    outline = Color(0xFF3C2A66)
)


private val LightColorScheme = lightColorScheme(
    primary = PinkPrimary,
    onPrimary = Color.White,
    primaryContainer = PinkPrimaryContainer,
    onPrimaryContainer = TextDark,

    secondary = BlueSecondary,
    onSecondary = Color.White,
    secondaryContainer = BlueSecondaryContainer,
    onSecondaryContainer = TextDark,

    background = BackgroundLight,
    onBackground = TextDark,
    surface = SurfaceLight,
    onSurface = TextDark,
    outline = OutlineLight
)


@Composable
fun ProyectoJuegoAndreaFFTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
