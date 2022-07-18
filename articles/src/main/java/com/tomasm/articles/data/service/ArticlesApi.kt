package com.tomasm.articles.data.service

import com.tomasm.articles.data.models.entity.ArticlesEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesApi {

    @GET("svc/mostpopular/v2/{type}/all-sections{share_type}/{period}.json")
    suspend fun getArticles(
        @Path("type") type: String,
        @Path("period") period: String,
        @Path("share_type", encoded = true) share_type: String = ""
    ): Response<ArticlesEntity>

}