package com.tomasm.articles.data.models.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.tomasm.articles.data.models.data.Article

@Entity
data class ArticleEntity(
    @SerializedName("id")
    val id: Long?,
    val url: String?,
    val published_date: String?,
    val section: String?,
    val title: String?,
    val byline: String?,
    val media: MutableList<MediaEntity>?
) {

    fun toArticle() =
        Article(
            id,
            url,
            published_date,
            section,
            title,
            byline,
            getFirstImageIfExist(media)
        )

    private fun getFirstImageIfExist(media: MutableList<MediaEntity>?): String {
        if(!media.isNullOrEmpty()){
            if(!media[0].mediaMetadata.isNullOrEmpty()){
                return media[0].mediaMetadata?.get(0)?.url ?: ""
            }
        }
        return ""
    }
}
