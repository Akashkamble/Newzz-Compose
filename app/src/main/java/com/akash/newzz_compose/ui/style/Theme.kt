package com.akash.newzz_compose.ui.style

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Created by Akash on 28/08/20
 */
val deepPurple = Color(0xFF4a148c)
val darkColor = Color(0xFF121212)

val sourceTextColor = Color(0xFF474646)
val sourceTextColorDark = Color(0xFF868080)

val titleColor = Color.Black
val titleColorDark = Color(0xB3DCDCDC)

val listBackgroundColor = Color.White
val listBackgroundColorDark = Color(0xFF1E1E1E)

val bottomNavBackground = Color.White
val bottomNavBackgroundDark = Color(0xFF222222)

val bottomNavIconActiveColor = Color(0xFF4a148c)
val bottomNavIconInActiveColor = Color.Black

val bottomNavIconActiveColorDark = Color(0xB3DCDCDC)
val bottomNavIconInActiveColorDark = Color(0xFF5C5757)

val circularLoaderColor = deepPurple
val circularLoaderColorDark = Color.White

val dividerColor = Color(0xFFDCDCDC)
val dividerColorDark = Color(0xFF2B2929)

private val lightTheme = lightColors(
    primary = deepPurple,
    onPrimary = Color.White,
    primaryVariant = deepPurple,
    surface = listBackgroundColor,
    onSurface = titleColor,
    secondary = sourceTextColor,
    onSecondary = Color.White,
    background = listBackgroundColor,
    onBackground = titleColor,
    secondaryVariant = deepPurple,
    error = Color.Black,
    onError = Color.White
)

private val darkTheme = darkColors(
    primary = darkColor,
    onPrimary = Color.White,
    primaryVariant = darkColor,
    surface = listBackgroundColorDark,
    onSurface = titleColorDark,
    secondary = sourceTextColorDark,
    onSecondary = Color.White,
    background = listBackgroundColorDark,
    onBackground = titleColorDark,
    secondaryVariant = darkColor,
    error = Color.White,
    onError = Color.Black
)

@Composable
fun NewzzTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    val colors = if (isDarkTheme) darkTheme else lightTheme
    MaterialTheme(
        colors = colors,
        content = content
    )
}
