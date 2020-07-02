package com.akash.newzz.data.repository

import com.akash.newzz.data.Result
import com.akash.newzz.data.apiservice.NewzzApiService
import com.akash.newzz.data.response.NewsError
import com.akash.newzz.data.response.NewsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Akash on 06/06/20
 */

class NewsRepositoryImpl constructor(
    private val newsApiService: NewzzApiService,
    private val moshi: Moshi
) : NewsRepository {
    override suspend fun getArticlesByCategoryAsync(
        category: String,
        page: Int
    ): Result<NewsResponse> {
        try {
            val response = newsApiService.getArticlesByCateGoryAsync(category)
            return if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error("No Data found")
                }
            } else {
                val jsonAdapter: JsonAdapter<NewsError> = moshi.adapter(
                    NewsError::class.java
                )
                withContext(Dispatchers.IO) {
                    val newsError = jsonAdapter.fromJson(response.errorBody()?.string()!!)
                    Result.Error(
                        newsError!!.message,
                        showRetry(newsError.code)
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            var errorMessage = e.localizedMessage
            if (e.localizedMessage!!.contains("Unable to resolve host")) {
                errorMessage = "No internet connection"
            }
            return Result.Error(errorMessage ?: "Something went wrong")
        }
    }

    private fun showRetry(code: String): Boolean = when (code) {
        "apiKeyDisabled", "apiKeyExhausted", "apiKeyInvalid", "apiKeyMissing" -> false
        else -> true
    }
}
