package com.tomasm.articles.data.models.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ArticlesView(
    val results: MutableList<ArticleView>?
) : Parcelable

