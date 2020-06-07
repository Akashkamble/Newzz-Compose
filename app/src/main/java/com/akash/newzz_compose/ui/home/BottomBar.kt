package com.akash.newzz_compose.ui.home

import androidx.compose.Composable
import androidx.compose.State
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Row
import androidx.ui.livedata.observeAsState
import androidx.ui.material.BottomAppBar
import androidx.ui.material.IconButton
import androidx.ui.res.vectorResource
import com.akash.newzz_compose.R
import com.akash.newzz_compose.style.*
import com.akash.newzz_compose.viewmodel.NewzzViewModel

/**
 * Created by Akash on 05/06/20
 */

@Composable
fun BottomBar(viewModel: NewzzViewModel) {
    val isDark = viewModel.isDarkTheme.observeAsState(false)
    BottomAppBar(backgroundColor = if (isDark.value) bottomNavBackgroundDark else bottomNavBackground) {
        val page = viewModel.pageNumber.observeAsState(NewzzViewModel.General)
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BottomNavItem(
                asset = R.drawable.ic_general,
                isSelected = page.value == NewzzViewModel.General,
                isDark = isDark,
                onClick = {
                    if (page.value != NewzzViewModel.General) {
                        viewModel.performAction(
                            NewzzViewModel.Action.ChangePageTo(
                                NewzzViewModel.General
                            )
                        )
                    }
                }
            )
            BottomNavItem(
                asset = R.drawable.ic_business,
                isSelected = page.value == NewzzViewModel.Business,
                isDark = isDark,
                onClick = {
                    if (page.value != NewzzViewModel.Business) {
                        viewModel.performAction(
                            NewzzViewModel.Action.ChangePageTo(
                                NewzzViewModel.Business
                            )
                        )
                    }
                }
            )
            BottomNavItem(
                asset = R.drawable.ic_tech,
                isSelected = page.value == NewzzViewModel.Technology,
                isDark = isDark,
                onClick = {
                    if (page.value != NewzzViewModel.Technology) {
                        viewModel.performAction(
                            NewzzViewModel.Action.ChangePageTo(
                                NewzzViewModel.Technology
                            )
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun BottomNavItem(asset: Int, isDark: State<Boolean>, onClick: () -> Unit, isSelected: Boolean = false) {
    IconButton(onClick = onClick) {
        Icon(
            asset = vectorResource(id = asset),
            tint = if (isDark.value) {
                if (isSelected) bottomNavIconActiveColorDark else bottomNavIconInActiveColorDark
            } else {
                if (isSelected) bottomNavIconActiveColor else bottomNavIconInActiveColor
            }
        )
    }
}