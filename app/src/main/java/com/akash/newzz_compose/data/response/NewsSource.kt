package com.akash.newzz_compose.data.response

import com.squareup.moshi.JsonClass

/**
 * Created by Akash on 06/06/20
 */

@JsonClass(generateAdapter = true)
data class NewsSource(
    val name: String?
)