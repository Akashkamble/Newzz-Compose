package com.akash.newzz_compose.ui.newzzappui

import com.akash.newzz.data.Result
import com.akash.newzz.data.response.NewsArticle

/**
 * Created by Akash on 29/08/20
 */

data class ArticleListUiState(
        val isLoading: Boolean = true,
        val list: List<NewsArticle>? = emptyList(),
        val error: Result.Error? = null
)