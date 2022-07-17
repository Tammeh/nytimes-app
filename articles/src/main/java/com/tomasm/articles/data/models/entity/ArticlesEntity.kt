package com.tomasm.articles.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tomasm.articles.data.models.data.Articles
import com.tomasm.core.extensions.orEmpty

@Entity
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val results: MutableList<ArticleEntity>?
) {

    fun toArticles() = Articles(
        numResults,
        results?.map {
            it.toArticle()
        }?.toMutableList().orEmpty()
    )
}