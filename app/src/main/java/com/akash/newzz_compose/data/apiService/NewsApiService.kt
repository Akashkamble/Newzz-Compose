package com.akash.newzz_compose.data.apiService

/**
 * Created by Akash on 06/06/20
 */
import com.akash.newzz_compose.Constants
import com.akash.newzz_compose.NewsApplication
import com.akash.newzz_compose.data.response.NewsResponse
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.File
import java.util.concurrent.TimeUnit

interface NewsApiService {


    @GET("top-headlines?sortBy=publishedAt&pageSize=25")
    suspend fun getArticlesByCateGoryAsync(
        @Query("category") category: String,
        @Query("country") country: String = Constants.COUNTRY
    ): Response<NewsResponse>


    companion object {

        private const val HEADER_CACHE_CONTROL = "Cache-Control"
        private const val HEADER_PRAGMA = "Pragma"
        private const val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB


        operator fun invoke(): NewsApiService {
            val requestInterceptor = Interceptor { chain ->

                val response = chain.proceed(chain.request())

                val cacheControl = CacheControl.Builder()
                    .maxAge(5, TimeUnit.SECONDS)
                    .build()

                return@Interceptor response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }

            val cache = Cache(
                File(NewsApplication.instances.cacheDir, "networkCache"),
                cacheSize
            )

            val offlineInterceptor = Interceptor { chain ->


                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("apiKey", Constants.API_KEY)
                    .build()

                var request = chain.request()

                if (NewsApplication.isNetworkConnected()) {

                    request = request
                        .newBuilder()
                        .url(url)
                        .build()
                } else {

                    val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()

                    request = request
                        .newBuilder()
                        .url(url)
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()

                }

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(offlineInterceptor)
                .addNetworkInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(NewsApiService::class.java)
        }
    }
}
