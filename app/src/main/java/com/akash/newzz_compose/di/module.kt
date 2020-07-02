package com.akash.newzz_compose.di

import com.akash.newzz.data.apiservice.NewzzApiService
import com.akash.newzz.data.repository.NewsRepository
import com.akash.newzz.data.repository.NewsRepositoryImpl
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
    single { NewzzApiService() }
    single { Moshi.Builder().build() }
}
