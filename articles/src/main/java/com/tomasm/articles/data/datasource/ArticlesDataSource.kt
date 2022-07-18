package com.tomasm.articles.data.datasource

import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.core.functional.Response
import kotlinx.coroutines.flow.Flow

interface ArticlesDataSource {

    fun getArticles(type: String, period: String, share_type: String) : Flow<Response<ArticlesView>>

}