package com.tomasm.articles.data.models.data

import com.tomasm.articles.data.models.view.ArticleView
import com.tomasm.core.extensions.orEmpty

data class Article(
    val id: Long?,
    val url: String?,
    val publishedDate: String?,
    val section: String?,
    val title: String?,
    val byline: String?,
    val imageURL: String?
) {

    fun toArticleView() = ArticleView(
        id.orEmpty(),
        url.orEmpty(),
        publishedDate.orEmpty(),
        section.orEmpty(),
        title.orEmpty(),
        byline.orEmpty(),
        imageURL.orEmpty()
    )
}
