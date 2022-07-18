package com.tomasm.articles.data.models.data

import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.core.extensions.orEmpty

data class Articles(
    val numResults: Int?,
    val results: MutableList<Article>?
) {


    fun toArticlesView() = ArticlesView(
        results?.map {it.toArticleView()}?.toMutableList().orEmpty()
    )
}