package com.tomasm.articles.data.datasource

import com.tomasm.articles.data.local.ArticlesLocal
import com.tomasm.articles.data.models.entity.ArticlesEntity
import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.articles.data.service.ArticlesService
import com.tomasm.core.exception.Failure
import com.tomasm.core.functional.Error
import com.tomasm.core.functional.Response
import com.tomasm.core.functional.Success
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class ArticlesDataSourceImpl @Inject constructor(
    private val service: ArticlesService,
    private val local: ArticlesLocal
) : ArticlesDataSource {
    override fun getArticles(type: String, period: String, share_type: String) = flow {
        emit(getArticlesFromService(type, period, share_type))
    }

    private suspend fun getArticlesFromService(
        type: String,
        period: String,
        share_type: String
    ): Response<ArticlesView> {
        return try {
            return service.getArticles(type, period, share_type).run {
                if (isSuccessful && body() != null) {
                    val results = body()
                    saveLocal(results!!)
                    Success(results.toArticles().toArticlesView())
                } else {
                    getArticlesFromDataBase(message())
                }
            }
        } catch (e: Exception) {
            getArticlesFromDataBase(e.message?: "Ocurrio un error inesperado")
        }
    }

    private fun getArticlesFromDataBase(code: String): Response<ArticlesView> {
        val articles = local.getArticles()
        return if (!articles.results.isNullOrEmpty()) {
            Success(articles.toArticles().toArticlesView())
        } else {
            Error(Failure.CustomError(0, code))
        }
    }

    private fun saveLocal(articles: ArticlesEntity) {
        local.putArticles(articles)
    }
}