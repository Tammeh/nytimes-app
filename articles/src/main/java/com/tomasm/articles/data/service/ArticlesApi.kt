package com.tomasm.articles.data.service

import com.tomasm.articles.data.models.entity.ArticlesEntity
import com.tomasm.core.platform.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesApi {

    @GET("/{type}/all-sections/{period}/{share_type}.json")
    suspend fun getArticles(
        @Path("type") type: String,
        @Path("period") period: String,
        @Path("share_type") share_type: String = ""
    ): Response<BaseResponse<ArticlesEntity>>

}