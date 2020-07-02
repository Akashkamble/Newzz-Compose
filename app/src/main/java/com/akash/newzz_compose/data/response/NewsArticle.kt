package com.akash.newzz_compose.data.response

import com.squareup.moshi.JsonClass

/**
 * Created by Akash on 06/06/20
 */

@JsonClass(generateAdapter = true)
data class NewsArticle(
    val publishedAt: String,

    val source: NewsSource,

    val title: String,

    val description: String? = null,

    val url: String? = null,

    val urlToImage: String? = null
)
