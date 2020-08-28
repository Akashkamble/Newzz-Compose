package com.akash.newzz_compose.ui.newzzappui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.akash.newzz_compose.other.Category
import com.akash.newzz_compose.other.getTitleResource
import com.akash.newzz_compose.ui.style.NewzzTheme
import com.akash.newzz_compose.viewmodel.NewzzViewModel

/**
 * Created by Akash on 28/08/20
 */

@Composable
fun NewzzAppUI(viewModel: NewzzViewModel) {
    val categoryList = viewModel.categoryList.observeAsState().value!!
    val activeCategory = viewModel.activeCategory.observeAsState().value!!
    Scaffold(
        bodyContent = {
            BodyContent(
                activeCategory = activeCategory,
                onThemeSwitch = {
                    viewModel.performAction(NewzzViewModel.Action.SwitchTheme)
                }
            )
        },
        bottomBar = {
            BottomBar(
                categoryList = categoryList,
                onMenuClicked = { category ->
                    viewModel.performAction(NewzzViewModel.Action.ChangePageTo(category))
                },
                activeCategory = activeCategory
            )
        }
    )
}

@Composable
fun BodyContent(activeCategory: Category, onThemeSwitch: () -> Unit) {
    val stringRes = getTitleResource(activeCategory)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = NewzzTheme.colors.primaryColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(stringRes, onThemeSwitch = {
                onThemeSwitch()
            })
        }
    }
}

