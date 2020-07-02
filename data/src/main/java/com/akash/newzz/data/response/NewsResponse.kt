package com.akash.newzz.data.response

import com.squareup.moshi.JsonClass

/**
 * Created by Akash on 06/06/20
 */
@JsonClass(generateAdapter = true)
data class NewsResponse(
    val articles: List<NewsArticle>,

    var status: String,

    val totalResults: Int
)
