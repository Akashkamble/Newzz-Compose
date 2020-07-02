package com.akash.newzz.data.repository

import com.akash.newzz.data.Result
import com.akash.newzz.data.response.NewsResponse

/**
 * Created by Akash on 06/06/20
 */
interface NewsRepository {
    suspend fun getArticlesByCategoryAsync(category: String, page: Int): Result<NewsResponse>
}
