package com.akash.newzz_compose.di

import com.akash.newzz.data.repository.NewsRepository
import com.akash.newzz.data.repository.NewsRepositoryImpl
import com.akash.newzz.data.repository.starwars.StarWarsRepo
import com.akash.newzz.data.repository.starwars.StarWarsRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepo(
        newsRepository: NewsRepositoryImpl
    ): NewsRepository

    @Binds
    abstract fun bindStarWarsRepo(
        starWarsRepo: StarWarsRepoImpl
    ) : StarWarsRepo
}
