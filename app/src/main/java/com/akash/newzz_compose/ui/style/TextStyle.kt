package com.akash.newzz_compose.ui.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Created by Akash on 28/08/20
 */

val categoryTitleStyle = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    letterSpacing = 1.1.sp,
    color = Color.White
)

val articleTitleStyle = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    color = titleColor
)

val sourceTextStyle = TextStyle(
    fontSize = 14.sp,
    color = sourceTextColor
)

val dateTextStyle = TextStyle(
    fontWeight = FontWeight.ExtraLight,
    fontSize = 12.sp,
    color = sourceTextColor
)