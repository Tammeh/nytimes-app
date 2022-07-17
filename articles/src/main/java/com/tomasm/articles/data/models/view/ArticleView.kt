package com.tomasm.articles.data.models.view

import android.os.Parcelable
import com.tomasm.articles.data.models.data.Media
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleView(
    val id: Int,
    val url: String,
    val publishedDate: String,
    val section: String,
    val title: String,
    val source: String,
    val imageURL: String
) : Parcelable
