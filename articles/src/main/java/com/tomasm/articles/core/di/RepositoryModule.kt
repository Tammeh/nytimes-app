package com.tomasm.articles.core.di

import com.tomasm.articles.data.repository.ArticlesRespositoryImpl
import com.tomasm.articles.domain.repository.ArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindArticlesRepository(articlesRepositoryImpl: ArticlesRespositoryImpl): ArticlesRepository
}