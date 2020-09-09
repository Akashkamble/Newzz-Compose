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
    val firstCategoryUiState = viewModel.firstCategoryUiState.observeAsState().value!!
    val secondCategoryUiState = viewModel.secondCategoryUiState.observeAsState().value!!
    val thirdCategoryUiState = viewModel.thirdCategoryUiState.observeAsState().value!!
    val listOfUiState = listOf(
            firstCategoryUiState,
            secondCategoryUiState,
            thirdCategoryUiState
    )
    val activeIndex = categoryList.indexOf(activeCategory)
    Scaffold(
            bodyContent = {
                BodyContent(
                        activeCategory = activeCategory,
                        onThemeSwitch = {
                            viewModel.performAction(NewzzViewModel.Action.SwitchTheme)
                        },
                        activeCategoryUiStateList = listOfUiState,
                        activeIndex = activeIndex,
                        retryFetchingArticles = { category ->
                            viewModel.performAction(NewzzViewModel.Action.FetchArticles(category))
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
fun BodyContent(
        activeCategory: Category,
        activeCategoryUiStateList: List<ArticleListUiState>,
        onThemeSwitch: () -> Unit,
        activeIndex: Int,
        retryFetchingArticles: (Category) -> Unit
) {
    val stringRes = getTitleResource(activeCategory)
    Surface(
            modifier = Modifier.fillMaxSize(),
            color = NewzzTheme.colors.primaryColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(stringRes, onThemeSwitch = {
                onThemeSwitch()
            })
            /* This when statement does not make any sense. Need to figure out better solution.
               the idea was to change uiState based on activeCategory, but when 2 category has different number of articles
               and you switch between pages LazyColumnFor remembers scroll position of previous state which leads to ArrayIndexOutOfBound Exception.
             */
            when (activeIndex) {
                0 -> {
                    NewzzListContainer(
                            uiState = activeCategoryUiStateList[activeIndex],
                            retry = {
                                retryFetchingArticles(activeCategory)
                            }
                    )
                }
                1 -> {
                    NewzzListContainer(
                            uiState = activeCategoryUiStateList[activeIndex],
                            retry = {
                                retryFetchingArticles(activeCategory)
                            }
                    )
                }
                2 -> {
                    NewzzListContainer(
                            uiState = activeCategoryUiStateList[activeIndex],
                            retry = {
                                retryFetchingArticles(activeCategory)
                            }
                    )
                }
            }

        }
    }
}
