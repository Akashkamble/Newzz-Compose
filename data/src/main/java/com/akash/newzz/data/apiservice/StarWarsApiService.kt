package com.akash.newzz.data.apiservice

import com.akash.newzz.data.response.NewsResponse
import com.akash.newzz.data.response.starwars.StarWarsResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface StarWarsApiService {

    @GET("project.json")
    suspend fun getStarWarsCharacters() : Response<StarWarsResponse>

    companion object {
        operator fun invoke() : StarWarsApiService {
            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl("https://duet-public-content.s3.us-east-2.amazonaws.com/")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(StarWarsApiService::class.java)
        }
    }
}