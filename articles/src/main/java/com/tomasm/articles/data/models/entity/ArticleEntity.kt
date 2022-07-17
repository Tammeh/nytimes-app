package com.tomasm.articles.data.models.entity

import com.tomasm.articles.data.models.data.Article


data class ArticleEntity(
    val id: Int?,
    val url: String?,
    val published_date: String?,
    val section: String?,
    val title: String?,
    val source: String?,
    val media: MutableList<MediaEntity>?
) {

    fun toArticle() =
        Article(
            id,
            url,
            published_date,
            section,
            title,
            source,
            getFirstImageIfExist(media)
        )

    private fun getFirstImageIfExist(media: MutableList<MediaEntity>?): String {
        return media?.get(0)?.mediaMetadata?.get(0)?.url ?: ""

    }
}
