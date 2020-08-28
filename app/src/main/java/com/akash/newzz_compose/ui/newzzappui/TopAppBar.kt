package com.akash.newzz_compose.ui.newzzappui

import androidx.annotation.StringRes
import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.akash.newzz_compose.R
import com.akash.newzz_compose.ui.style.categoryTitleStyle

/**
 * Created by Akash on 28/08/20
 */

@Composable
fun TopAppBar(@StringRes titleResource: Int, onThemeSwitch: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalGravity = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = stringResource(id = titleResource),
            style = categoryTitleStyle
        )
        Box(modifier = Modifier.padding(top = 24.dp, end = 8.dp)) {
            ThemeSwitcher(onThemeSwitch = {
                onThemeSwitch()
            })
        }
    }
}

@Composable
fun ThemeSwitcher(onThemeSwitch: () -> Unit) {
    val isDark = remember { mutableStateOf(false) }
    val light = vectorResource(id = R.drawable.ic_light)
    val dark = vectorResource(id = R.drawable.ic_dark)
    IconButton(onClick = {
        onThemeSwitch()
        isDark.value = !isDark.value
    }) {
        Icon(
            asset = if (isDark.value) light else dark,
            tint = Color.White
        )
    }
}