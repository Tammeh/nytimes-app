package com.tomasm.articles.domain.repository

import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.core.functional.Response
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    fun getArticles(type: String, period: String, share_type: String) : Flow<Response<ArticlesView>>

}