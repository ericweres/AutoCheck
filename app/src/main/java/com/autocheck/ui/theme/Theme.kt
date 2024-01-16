package com.autocheck.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Definition des AutoCheck-Themas basierend auf dem Dunkel- oder Hell-Modus.
 */

// Dunkles Farbschema
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Helles Farbschema
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
    // Fügen Sie hier bei Bedarf weitere Standardfarben hinzu
)

@Composable
fun AutoCheckTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamische Farbe ist ab Android 12 verfügbar
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Bestimmen Sie das Farbschema basierend auf dem Dunkel- oder Hell-Modus und der Android-Version
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Aktualisieren Sie die StatusBar-Farbe und den StatusBar-Text basierend auf dem Farbschema
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    // Wenden Sie das MaterialTheme unter Verwendung des ausgewählten Farbschemas und der standardmäßigen Schriftarten an
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
