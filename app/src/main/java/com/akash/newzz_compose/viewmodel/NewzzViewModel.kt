package com.akash.newzz_compose.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.newzz.data.Result
import com.akash.newzz.data.repository.NewsRepository
import com.akash.newzz.data.response.NewsResponse
import com.akash.newzz_compose.other.Business
import com.akash.newzz_compose.other.Category
import com.akash.newzz_compose.other.General
import com.akash.newzz_compose.other.Technology
import com.akash.newzz_compose.ui.newzzappui.ArticleListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Akash on 28/08/20
 */
class NewzzViewModel @ViewModelInject constructor(private val repo: NewsRepository) : ViewModel() {

    private val _isDarkTheme = MutableLiveData<Boolean>().apply { value = false }
    val isDarkTheme: LiveData<Boolean> = _isDarkTheme

    private val _categoryList = MutableLiveData<List<Category>>().apply {
        value = listOf(General(), Business(), Technology())
    }
    val categoryList: LiveData<List<Category>> = _categoryList

    private val _activeCategory = MutableLiveData<Category>().apply {
        value = categoryList.value!![0]
    }
    val activeCategory: LiveData<Category> = _activeCategory

    private val _activeCategoryUiState = MutableLiveData<ArticleListUiState>().apply {
        value = ArticleListUiState()
    }
    val activeCategoryUiState: LiveData<ArticleListUiState> = _activeCategoryUiState

    private var firstPageUiState = ArticleListUiState()
    private var secondPageUiState = ArticleListUiState()
    private var thirdPageUiState = ArticleListUiState()

    init {
        getArticlesByCategory(categoryList.value!![0])
    }

    private fun getArticlesByCategory(
            category: Category,
            page: Int = 1
    ) {
        viewModelScope.launch {
            setLoadingState(category)
            when (val result = repo.getArticlesByCategoryAsync(category.category, page)) {
                is Result.Error -> {
                    withContext(Dispatchers.Main) {
                        setErrorState(category, Result.Error(result.errorMessage, result.showRetry))
                    }
                }
                is Result.Success -> {
                    withContext(Dispatchers.Main) {
                        setSuccessState(category, result.data)
                    }
                }
            }
        }
    }

    fun performAction(action: Action) {
        when (action) {
            is Action.ChangePageTo -> {
                _activeCategory.value = action.category
                when (categoryList.value!!.indexOf(action.category)) {
                    0 -> _activeCategoryUiState.value = firstPageUiState
                    1 -> _activeCategoryUiState.value = secondPageUiState
                    2 -> _activeCategoryUiState.value = thirdPageUiState
                }
                if (activeCategoryUiState.value!!.list == null || activeCategoryUiState.value!!.list!!.isEmpty()) {
                    getArticlesByCategory(action.category)
                }
            }
            is Action.FetchArticles -> {
                getArticlesByCategory(action.category)
            }
            Action.SwitchTheme -> {
                _isDarkTheme.value = !_isDarkTheme.value!!
            }
        }
    }

    private fun setErrorState(category: Category, error: Result.Error) {
        when (categoryList.value!!.indexOf(category)) {
            0 -> {
                firstPageUiState = firstPageUiState.copy(
                        isLoading = false,
                        list = null,
                        error = error
                )
                _activeCategoryUiState.value = firstPageUiState
            }
            1 -> {
                secondPageUiState = secondPageUiState.copy(
                        isLoading = false,
                        list = null,
                        error = error
                )
                _activeCategoryUiState.value = secondPageUiState
            }
            2 -> {
                thirdPageUiState = thirdPageUiState.copy(
                        isLoading = false,
                        list = null,
                        error = error
                )
                _activeCategoryUiState.value = thirdPageUiState
            }
        }
    }

    private fun setSuccessState(category: Category, data: NewsResponse) {
        when (categoryList.value!!.indexOf(category)) {
            0 -> {
                firstPageUiState = firstPageUiState.copy(
                        isLoading = false,
                        list = data.articles,
                        error = null
                )
                _activeCategoryUiState.value = firstPageUiState
            }
            1 -> {
                secondPageUiState = secondPageUiState.copy(
                        isLoading = false,
                        list = data.articles,
                        error = null
                )
                _activeCategoryUiState.value = secondPageUiState
            }
            2 -> {
                thirdPageUiState = thirdPageUiState.copy(
                        isLoading = false,
                        list = data.articles,
                        error = null
                )
                _activeCategoryUiState.value = thirdPageUiState
            }
        }
    }

    private fun setLoadingState(category: Category) {
        when (categoryList.value!!.indexOf(category)) {
            0 -> {
                _activeCategoryUiState.value = firstPageUiState.copy(isLoading = true)
            }
            1 -> {
                _activeCategoryUiState.value = secondPageUiState.copy(isLoading = true)
            }
            2 -> {
                _activeCategoryUiState.value = thirdPageUiState.copy(isLoading = true)
            }
        }
    }

    sealed class Action {
        data class ChangePageTo(val category: Category) : Action()
        data class FetchArticles(val category: Category) : Action()
        object SwitchTheme : Action()
    }
}
