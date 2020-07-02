package com.akash.newzz_compose.di

import com.akash.newzz_compose.data.apiservice.NewsApiService
import com.akash.newzz_compose.data.repository.NewsRepository
import com.akash.newzz_compose.data.repository.NewsRepositoryImpl
import com.akash.newzz_compose.viewmodel.NewzzViewModel
import com.squareup.moshi.Moshi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Akash on 06/06/20
 */

val appModule = module {
    viewModel { NewzzViewModel(get()) }
    single { NewsRepositoryImpl(get(), get()) as NewsRepository }
    single { NewsApiService() }
    single { Moshi.Builder().build() }
}
