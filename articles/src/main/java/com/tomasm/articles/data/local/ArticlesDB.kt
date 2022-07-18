package com.tomasm.articles.data.local

import com.tomasm.articles.data.models.entity.ArticlesEntity

interface ArticlesDB {
    fun getArticles(): ArticlesEntity?
    fun putArticles(articles: ArticlesEntity)
    fun updateArticles(articles: ArticlesEntity)
}