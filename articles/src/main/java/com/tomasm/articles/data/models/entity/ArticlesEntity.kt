package com.tomasm.articles.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.tomasm.articles.core.database.typeconverters.ArticlesTypeConverter
import com.tomasm.articles.data.models.data.Articles
import com.tomasm.core.extensions.orEmpty

@Entity
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    @TypeConverters(ArticlesTypeConverter::class)
    val results: MutableList<ArticleEntity>?
) {

    fun toArticles() = Articles(
        numResults,
        results?.map {
            it.toArticle()
        }?.toMutableList().orEmpty()
    )
}