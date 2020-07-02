package com.akash.newzz_compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akash.newzz_compose.Category
import com.akash.newzz_compose.data.repository.NewsRepository
import com.akash.newzz_compose.data.response.NewsArticle
import com.akash.newzz_compose.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext

/**
 * Created by Akash on 06/06/20
 */

class NewzzViewModel(private val repo: NewsRepository) : ViewModel() {

    private val job = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main) + job

    private val _pageNumber = MutableLiveData<Int>().apply {
        value = General
    }
    val pageNumber: LiveData<Int> = _pageNumber

    private val _isDarkTheme = MutableLiveData<Boolean>().apply { value = false }
    val isDarkTheme: LiveData<Boolean> = _isDarkTheme

    private val generalArticlesListState = ArticleState()
    private val businessArticlesListState = ArticleState()
    private val techArticlesListState = ArticleState()

    private val _generalState = MutableLiveData<ArticleState>().apply {
        value = generalArticlesListState
    }
    val generalState: LiveData<ArticleState> = _generalState

    private val _businessState = MutableLiveData<ArticleState>().apply {
        value = businessArticlesListState
    }
    val businessState: LiveData<ArticleState> = _businessState

    private val _techState = MutableLiveData<ArticleState>().apply {
        value = techArticlesListState
    }
    val techState: LiveData<ArticleState> = _techState

    init {
        performAction(Action.FetchArticles(Category.GENERAL))
    }

    fun performAction(action: Action) {
        when (action) {
            is Action.ChangePageTo -> {
                _pageNumber.value = action.page
                when (action.page) {
                    General -> performAction(
                        Action.FetchArticles(Category.GENERAL)
                    )
                    Business -> performAction(
                        Action.FetchArticles(Category.BUSINESS)
                    )
                    Technology -> performAction(
                        Action.FetchArticles(Category.TECH)
                    )
                }
            }
            is Action.FetchArticles -> viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    fetArticles(action.category)
                }
            }
            is Action.SwitchTheme -> {
                _isDarkTheme.value = !_isDarkTheme.value!!
            }
        }
    }

    private suspend fun fetArticles(category: String) {
        when (category) {
            Category.GENERAL -> fetchGeneralArticles(category)
            Category.BUSINESS -> fetchBusinessArticles(category)
            Category.TECH -> fetchTechArticles(category)
        }
    }

    private suspend fun fetchGeneralArticles(category: String) {
        val state = generalState.value!!
        if (state.list == null || state.list.isEmpty()) {
            withContext(Dispatchers.Main) {
                val articleState = ArticleState()
                _generalState.value = articleState
            }
            when (val result = repo.getArticlesByCategoryAsync(category, 1)) {
                is Result.Success -> {
                    withContext(Dispatchers.Main) {
                        val articleState = generalArticlesListState.copy(
                            isLoading = false,
                            list = result.data.articles,
                            error = null
                        )
                        _generalState.value = articleState
                    }
                }
                is Result.Error -> {
                    withContext(Dispatchers.Main) {
                        val articleState = generalArticlesListState.copy(
                            isLoading = false,
                            list = null,
                            error = Error(result.errorMessage, result.showRetry)
                        )
                        _generalState.value = articleState
                    }
                }
            }
        }
    }

    private suspend fun fetchBusinessArticles(category: String) {
        val state = businessState.value!!
        if (state.list == null || state.list.isEmpty()) {
            withContext(Dispatchers.Main) {
                val articleState = ArticleState()
                _businessState.value = articleState
            }
            when (val result = repo.getArticlesByCategoryAsync(category, 1)) {
                is Result.Success -> {
                    withContext(Dispatchers.Main) {
                        val articleState = businessArticlesListState.copy(
                            isLoading = false,
                            list = result.data.articles,
                            error = null
                        )
                        _businessState.value = articleState
                    }
                }
                is Result.Error -> {
                    withContext(Dispatchers.Main) {
                        val articleState = businessArticlesListState.copy(
                            isLoading = false,
                            list = null,
                            error = Error(result.errorMessage, result.showRetry)
                        )
                        _businessState.value = articleState
                    }
                }
            }
        }
    }

    private suspend fun fetchTechArticles(category: String) {
        val state = techState.value!!
        if (state.list == null || state.list.isEmpty()) {
            withContext(Dispatchers.Main) {
                val articleState = ArticleState()
                _techState.value = articleState
            }
            when (val result = repo.getArticlesByCategoryAsync(category, 1)) {
                is Result.Success -> {
                    withContext(Dispatchers.Main) {
                        val articleState = techArticlesListState.copy(
                            isLoading = false,
                            list = result.data.articles,
                            error = null
                        )
                        _techState.value = articleState
                    }
                }
                is Result.Error -> {
                    withContext(Dispatchers.Main) {
                        val articleState = techArticlesListState.copy(
                            isLoading = false,
                            list = null,
                            error = Error(result.errorMessage, result.showRetry)
                        )
                        _techState.value = articleState
                    }
                }
            }
        }
    }

    sealed class Action {
        data class ChangePageTo(val page: Int) : Action()
        data class FetchArticles(val category: String) : Action()
        object SwitchTheme : Action()
    }

    data class ArticleState(
        val isLoading: Boolean = true,
        val list: List<NewsArticle>? = emptyList(),
        val error: Error? = null
    )

    data class Error(
        val errorMessage: String,
        val showRetry: Boolean = true
    )

    companion object {
        const val General = 1
        const val Business = 2
        const val Technology = 3
    }
}
