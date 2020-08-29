package com.akash.newzz_compose.di

import com.akash.newzz.data.apiservice.NewzzApiService
import com.akash.newzz.data.repository.NewsRepository
import com.akash.newzz.data.repository.NewsRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Created by Akash on 28/08/20
 */

@Module
@InstallIn(ActivityComponent::class)
object NewzzActivityModule {

    @Provides
    @ActivityScoped
    fun provideNewsApiService(): NewzzApiService = NewzzApiService()

    @Provides
    @ActivityScoped
    fun provideNewsRepo(
        apiService: NewzzApiService,
        moshi: Moshi
    ): NewsRepository = NewsRepositoryImpl(apiService, moshi)

    @Provides
    @ActivityScoped
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}
