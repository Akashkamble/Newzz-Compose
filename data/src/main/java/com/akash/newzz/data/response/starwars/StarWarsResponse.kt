package com.akash.newzz.data.response.starwars


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StarWarsResponse(
    @Json(name = "results")
    val characters: List<Character>?
)