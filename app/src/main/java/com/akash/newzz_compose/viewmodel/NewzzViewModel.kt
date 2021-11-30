package com.akash.newzz_compose.viewmodel

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
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Akash on 28/08/20
 */
@HiltViewModel
class NewzzViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {

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

    init {
        getArticlesByCategory(categoryList.value!![0])
    }

    private fun getArticlesByCategory(
        category: Category,
        page: Int = 1
    ) {
        viewModelScope.launch {
            val activeState = ArticleListUiState()
            setLoadingState(activeState)
            when (val result = repo.getArticlesByCategoryAsync(category.category, page)) {
                is Result.Error -> {
                    withContext(Dispatchers.Main) {
                        setErrorState(
                            activeState,
                            Result.Error(result.errorMessage, result.showRetry)
                        )
                    }
                }
                is Result.Success -> {
                    withContext(Dispatchers.Main) {
                        setSuccessState(activeState, result.data)
                    }
                }
            }
        }
    }

    fun performAction(action: Action) {
        when (action) {
            is Action.ChangePageTo -> {
                _activeCategory.value = action.category
                getArticlesByCategory(_activeCategory.value!!)
            }
            is Action.FetchArticles -> {
                getArticlesByCategory(action.category)
            }
            Action.SwitchTheme -> {
                _isDarkTheme.value = !_isDarkTheme.value!!
            }
        }
    }

    private fun setErrorState(activeState: ArticleListUiState, error: Result.Error) {
        _activeCategoryUiState.value = activeState.copy(
            isLoading = false,
            list = null,
            error = error
        )
    }

    private fun setSuccessState(activeState: ArticleListUiState, data: NewsResponse) {
        _activeCategoryUiState.value = activeState.copy(
            isLoading = false,
            list = data.articles,
            error = null
        )
    }

    private fun setLoadingState(activeState: ArticleListUiState) {
        _activeCategoryUiState.value = activeState.copy(isLoading = true)
    }

    sealed class Action {
        data class ChangePageTo(val category: Category) : Action()
        data class FetchArticles(val category: Category) : Action()
        object SwitchTheme : Action()
    }
}
