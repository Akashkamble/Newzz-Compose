package com.akash.newzz_compose.ui.home

import androidx.compose.Composable
import androidx.compose.State
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.livedata.observeAsState
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.IconButton
import androidx.ui.material.Surface
import androidx.ui.material.TextButton
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import com.akash.newzz_compose.Category
import com.akash.newzz_compose.R
import com.akash.newzz_compose.style.articleTitleStyle
import com.akash.newzz_compose.style.categoryTitleStyle
import com.akash.newzz_compose.style.circularLoaderColor
import com.akash.newzz_compose.style.circularLoaderColorDark
import com.akash.newzz_compose.style.deepPurple
import com.akash.newzz_compose.style.listBackgroundColor
import com.akash.newzz_compose.style.listBackgroundColorDark
import com.akash.newzz_compose.style.sourceTextColorDark
import com.akash.newzz_compose.style.titleColorDark
import com.akash.newzz_compose.ui.articlelist.ArticleList
import com.akash.newzz_compose.viewmodel.NewzzViewModel

/**
 * Created by Akash on 05/06/20
 */

@Composable
fun BodyContent(viewModel: NewzzViewModel) {
    val page = viewModel.pageNumber.observeAsState(NewzzViewModel.General)
    val generalState = viewModel.generalState.observeAsState(NewzzViewModel.ArticleState())
    val businessState = viewModel.businessState.observeAsState(NewzzViewModel.ArticleState())
    val techState = viewModel.techState.observeAsState(NewzzViewModel.ArticleState())
    val isDark = viewModel.isDarkTheme.observeAsState(false)
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopAppBar(viewModel)
            Surface(
                color = if (isDark.value) listBackgroundColorDark else listBackgroundColor,
                shape = RoundedCornerShape(topLeft = 5.dp, topRight = 5.dp),
                modifier = Modifier.fillMaxSize().padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 54.dp
                )
            ) {
                when (page.value) {
                    NewzzViewModel.General -> ArticleStateWidget(
                        state = generalState,
                        isDark = isDark,
                        onClick = {
                            viewModel.performAction(NewzzViewModel.Action.FetchArticles(Category.GENERAL))
                        }
                    )
                    NewzzViewModel.Business -> ArticleStateWidget(
                        state = businessState,
                        isDark = isDark,
                        onClick = {
                            viewModel.performAction(NewzzViewModel.Action.FetchArticles(Category.BUSINESS))
                        }
                    )
                    NewzzViewModel.Technology -> ArticleStateWidget(
                        state = techState,
                        isDark = isDark,
                        onClick = {
                            viewModel.performAction(NewzzViewModel.Action.FetchArticles(Category.TECH))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppBar(viewModel: NewzzViewModel) {
    val page = viewModel.pageNumber.observeAsState(NewzzViewModel.General)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalGravity = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = getTitle(page.value),
            style = categoryTitleStyle
        )
        Box(modifier = Modifier.padding(top = 24.dp, end = 8.dp)) {
            ThemeSwitcher(viewModel = viewModel)
        }
    }
}

@Composable
fun ArticleStateWidget(
    state: State<NewzzViewModel.ArticleState>,
    isDark: State<Boolean>,
    onClick: () -> Unit
) {
    when {
        state.value.isLoading -> {
            Loading(isDark)
        }
        state.value.list != null -> {
            ArticleList(articles = state.value.list!!, isDark = isDark)
        }
        else -> {
            ErrorView(
                errorMessage = state.value.error!!.errorMessage,
                showRetry = state.value.error!!.showRetry,
                onClick = onClick,
                isDark = isDark
            )
        }
    }
}

@Composable
fun ThemeSwitcher(viewModel: NewzzViewModel) {
    val isDark = viewModel.isDarkTheme.observeAsState(false)
    val light = vectorResource(id = R.drawable.ic_light)
    val dark = vectorResource(id = R.drawable.ic_dark)
    IconButton(onClick = {
        viewModel.performAction(NewzzViewModel.Action.SwitchTheme)
    }) {
        Icon(
            asset = if (isDark.value) light else dark,
            tint = Color.White
        )
    }
}

@Composable
fun Loading(isDark: State<Boolean>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = if (isDark.value) circularLoaderColorDark else circularLoaderColor
        )
    }
}

@Composable
fun ErrorView(
    errorMessage: String,
    showRetry: Boolean,
    isDark: State<Boolean>,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            style = if (isDark.value) articleTitleStyle.copy(color = titleColorDark) else articleTitleStyle
        )
        if (showRetry) {
            TextButton(onClick = onClick) {
                Text(
                    text = "Retry",
                    style = TextStyle(
                        color = if (isDark.value) sourceTextColorDark else deepPurple
                    )
                )
            }
        }
    }
}

private fun getTitle(pageNumber: Int): String = when (pageNumber) {
    1 -> "General"
    2 -> "Business"
    3 -> "Technology"
    else -> throw IllegalAccessException("Page number is invalid")
}
