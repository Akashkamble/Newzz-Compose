package com.akash.newzz_compose.style

import androidx.ui.graphics.Color
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.sp

/**
 * Created by Akash on 07/06/20
 */


val categoryTitleStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
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