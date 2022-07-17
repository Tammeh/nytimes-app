package com.tomasm.articles.data.repository

import com.tomasm.articles.data.datasource.ArticlesDataSource
import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.articles.domain.repository.ArticlesRepository
import com.tomasm.core.functional.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticlesRespositoryImpl @Inject constructor(
    private val articlesDataSource: ArticlesDataSource
) : ArticlesRepository {
    override fun getArticles(type: String, period: String, share_type: String) =
        articlesDataSource.getArticles(type, period, share_type)
}