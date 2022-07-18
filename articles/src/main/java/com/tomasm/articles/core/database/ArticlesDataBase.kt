package com.tomasm.articles.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tomasm.articles.core.dao.ArticlesDAO
import com.tomasm.articles.core.database.typeconverters.ArticlesTypeConverter
import com.tomasm.articles.data.models.entity.ArticlesEntity

@Database(entities = [ArticlesEntity::class], version = 1)
@TypeConverters(ArticlesTypeConverter::class)
abstract class ArticlesDataBase : RoomDatabase() {

    abstract fun getArticlesDao(): ArticlesDAO
}