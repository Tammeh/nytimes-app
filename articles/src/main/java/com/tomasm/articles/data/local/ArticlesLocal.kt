package com.tomasm.articles.data.local

import com.tomasm.articles.core.database.ArticlesDataBase
import com.tomasm.articles.data.models.entity.ArticlesEntity
import javax.inject.Inject

class ArticlesLocal @Inject constructor(articlesDataBase: ArticlesDataBase): ArticlesDB{

    private val articlesDAO by lazy {
        articlesDataBase.getArticlesDao()
    }

    override fun getArticles() = articlesDAO.getArticles()

    override fun putArticles(articles: ArticlesEntity) = articlesDAO.insert(articles)

    override fun updateArticles(articles: ArticlesEntity) = articlesDAO.update(articles)
}