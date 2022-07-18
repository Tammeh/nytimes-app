package com.tomasm.articles.core.dao

import androidx.room.Dao
import androidx.room.Query
import com.tomasm.articles.data.models.entity.ArticlesEntity
import com.tomasm.core.platform.BaseDAO

@Dao
interface ArticlesDAO : BaseDAO<ArticlesEntity> {

    @Query("SELECT * FROM ArticlesEntity" )
    fun getArticles() : ArticlesEntity
}