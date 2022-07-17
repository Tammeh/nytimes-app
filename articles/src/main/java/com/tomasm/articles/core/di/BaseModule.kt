package com.tomasm.articles.core.di

import com.tomasm.articles.data.datasource.ArticlesDataSource
import com.tomasm.articles.data.datasource.ArticlesDataSourceImpl
import com.tomasm.articles.data.local.ArticlesLocal
import com.tomasm.articles.data.service.ArticlesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseModule {

    @Singleton
    @Provides
    fun provideArticlesDataSource(service: ArticlesService, local: ArticlesLocal): ArticlesDataSource {
        return ArticlesDataSourceImpl(service, local)
    }



}