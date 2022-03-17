package com.akash.newzz.data.response.starwars


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "birth_year")
    val birthYear: String,
    @Json(name = "eye_color")
    val eyeColor: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "hair_color")
    val hairColor: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "mass")
    val mass: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "skin_color")
    val skinColor: String
) : Parcelable