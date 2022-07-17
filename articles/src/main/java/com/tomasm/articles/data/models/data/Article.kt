package com.tomasm.articles.data.models.data

import com.tomasm.articles.data.models.view.ArticleView
import com.tomasm.core.extensions.orEmpty

data class Article(
    val id: Int?,
    val url: String?,
    val publishedDate: String?,
    val section: String?,
    val title: String?,
    val source: String?,
    val media: MutableList<Media>?
) {

    fun toArticleView() = ArticleView(
        id.orEmpty(),
        url.orEmpty(),
        publishedDate.orEmpty(),
        section.orEmpty(),
        title.orEmpty(),
        source.orEmpty(),
        media.orEmpty()
    )
}
