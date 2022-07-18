package com.tomasm.articles.core.database.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tomasm.articles.data.models.entity.ArticleEntity
import com.tomasm.core.extensions.empty

object ArticlesTypeConverter {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun toArticle(data: String?): MutableList<ArticleEntity> {
        return if (data.isNullOrEmpty()) {
            mutableListOf()
        } else {
            gson.fromJson(data, Array<ArticleEntity>::class.java).map { it }
                .toMutableList()
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromArticle(data: MutableList<ArticleEntity>?): String {
        return if (data == null) {
            String.empty()
        } else {
            gson.toJson(data)
        }
    }

}