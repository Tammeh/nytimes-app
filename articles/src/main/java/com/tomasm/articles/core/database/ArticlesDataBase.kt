package com.tomasm.articles.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tomasm.articles.core.dao.ArticlesDAO
import com.tomasm.articles.data.models.entity.ArticlesEntity

@Database(entities = [ArticlesEntity::class], version = 1)
abstract class ArticlesDataBase : RoomDatabase() {

    abstract fun getArticlesDao(): ArticlesDAO
}