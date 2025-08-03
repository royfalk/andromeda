package il.co.falk.andromeda.ui.theme.il.co.falk.andromeda.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFFFFFFFF),
    onPrimary = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0x8E24AA),
    secondary = Color(0x6D6D7E),
    tertiary = Color(0xD81B60),
    // Define other dark mode colors here
)

@Composable
fun AndromedaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
    ) {

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(), // Define your typography in Typography.kt
        content = content
    )
}