package com.tomasm.articles.data.datasource

import com.tomasm.articles.data.local.ArticlesLocal
import com.tomasm.articles.data.models.entity.ArticlesEntity
import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.articles.data.service.ArticlesService
import com.tomasm.core.exception.Failure
import com.tomasm.core.functional.Error
import com.tomasm.core.functional.Response
import com.tomasm.core.functional.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticlesDataSourceImpl(
    private val service: ArticlesService,
    private val local: ArticlesLocal
) : ArticlesDataSource {
    override fun getArticles(type: String, period: String, share_type: String) = flow {
        emit(getArticlesFromService(type, period, share_type))
    }

    private suspend fun getArticlesFromService(type: String, period: String, share_type: String): Response<ArticlesView> {
        return service.getArticles(type, period, share_type).run {
            if(isSuccessful && body() != null){
                val results = body()!!.results
                saveLocal(results)
                Success(results.toArticles().toArticleView())
            }else{
                Error(Failure.ServerError(code()))
            }
        }
    }

    private fun saveLocal(articles: ArticlesEntity) {
        local.putArticles(articles)
    }
}