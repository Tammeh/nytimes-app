package com.tomasm.articles.core.di

import android.content.Context
import androidx.room.Room
import com.tomasm.articles.core.database.ArticlesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val ARTICLE_DATABASE_NAME = "articles_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ArticlesDataBase::class.java, ARTICLE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideArticlesDAO(dataBase: ArticlesDataBase) = dataBase.getArticlesDao()

}