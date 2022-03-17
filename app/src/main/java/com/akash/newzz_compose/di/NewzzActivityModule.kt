package com.akash.newzz_compose.di

import com.akash.newzz.data.apiservice.NewzzApiService
import com.akash.newzz.data.apiservice.StarWarsApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Akash on 28/08/20
 */

@Module
@InstallIn(SingletonComponent::class)
object NewzzActivityModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideNewsApiService(): NewzzApiService = NewzzApiService.invoke()

    @Provides
    @Singleton
    fun provideStarWarsApiService() : StarWarsApiService = StarWarsApiService.invoke()
}
