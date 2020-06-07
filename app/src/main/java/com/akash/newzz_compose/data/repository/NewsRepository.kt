package com.akash.newzz_compose.data.repository

import com.akash.newzz_compose.data.response.NewsResponse
import com.akash.newzz_compose.utils.Result

/**
 * Created by Akash on 06/06/20
 */
interface NewsRepository {
    suspend fun getArticlesByCategoryAsync(category: String, page: Int): Result<NewsResponse>
}