package com.akash.newzz_compose.data.response

import com.squareup.moshi.JsonClass

/**
 * Created by Akash on 06/06/20
 */

@JsonClass(generateAdapter = true)
data class NewsError(
    val code: String,

    val message: String,

    val status: String
)
