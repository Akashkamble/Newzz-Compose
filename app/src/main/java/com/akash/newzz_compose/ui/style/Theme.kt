package com.akash.newzz_compose.ui.style


import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
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

private val LightColorPalette = NewzzColorPalette(
    primaryColor = deepPurple,
    backGroundColor = listBackgroundColor,
    dividerColor = dividerColor,
    titleColor = titleColor,
    sourceColor = sourceTextColor,
    bottomNavBackground = bottomNavBackground,
    circularLoaderColor = circularLoaderColor,
    bottomNavActiveIconColor = bottomNavIconActiveColor,
    bottomNavInActiveIconColor = bottomNavIconInActiveColor,
    isDark = false
)

private val DarkColorPalette = NewzzColorPalette(
    primaryColor = darkColor,
    backGroundColor = listBackgroundColorDark,
    dividerColor = dividerColorDark,
    titleColor = titleColorDark,
    sourceColor = sourceTextColorDark,
    bottomNavBackground = bottomNavBackgroundDark,
    circularLoaderColor = circularLoaderColorDark,
    bottomNavActiveIconColor = bottomNavIconActiveColorDark,
    bottomNavInActiveIconColor = bottomNavIconInActiveColorDark,
    isDark = false
)

@Composable
fun NewzzTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    ProvideNewzzColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            content = content
        )
    }
}

object NewzzTheme {
    @Composable
    val colors: NewzzColorPalette
        get() = NewzzColorAmbient.current
}

/**
 * Jetsnack custom Color Palette
 */
@Stable
class NewzzColorPalette(
    primaryColor: Color,
    backGroundColor: Color,
    dividerColor: Color,
    titleColor: Color,
    sourceColor: Color,
    bottomNavBackground: Color,
    circularLoaderColor: Color,
    bottomNavActiveIconColor: Color,
    bottomNavInActiveIconColor: Color,
    isDark: Boolean
) {
    var primaryColor by mutableStateOf(primaryColor)
        private set
    var backGroundColor by mutableStateOf(backGroundColor)
        private set
    var dividerColor by mutableStateOf(dividerColor)
        private set
    var titleColor by mutableStateOf(titleColor)
        private set
    var sourceColor by mutableStateOf(sourceColor)
        private set
    var bottomNavBackground by mutableStateOf(bottomNavBackground)
        private set
    var circularLoaderColor by mutableStateOf(circularLoaderColor)
        private set
    var bottomNavActiveIconColor by mutableStateOf(bottomNavActiveIconColor)
        private set
    var bottomNavInActiveIconColor by mutableStateOf(bottomNavInActiveIconColor)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: NewzzColorPalette) {
        primaryColor = other.primaryColor
        backGroundColor = other.backGroundColor
        dividerColor = other.dividerColor
        titleColor = other.titleColor
        sourceColor = other.sourceColor
        bottomNavBackground = other.bottomNavBackground
        circularLoaderColor = other.circularLoaderColor
        bottomNavActiveIconColor = other.bottomNavActiveIconColor
        bottomNavInActiveIconColor = other.bottomNavInActiveIconColor
        isDark = other.isDark
    }
}

@Composable
fun ProvideNewzzColors(
    colors: NewzzColorPalette,
    content: @Composable () -> Unit
) {
//    colors.update(colors)
    Providers(NewzzColorAmbient provides colors, children = content)
}

private val NewzzColorAmbient = staticAmbientOf<NewzzColorPalette> {
    error("No NewzzColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [NewzzTheme.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)