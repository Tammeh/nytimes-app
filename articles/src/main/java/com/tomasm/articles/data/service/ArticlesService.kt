package com.tomasm.articles.data.service

import retrofit2.Retrofit
import javax.inject.Inject

class ArticlesService @Inject constructor(retrofit: Retrofit) : ArticlesApi {

    private val articlesApi by lazy { retrofit.create(ArticlesApi::class.java) }

    override suspend fun getArticles(
        type: String,
        period: String,
        share_type: String
    ) = articlesApi.getArticles(type, period, share_type)
}