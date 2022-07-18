package com.tomasm.articles.domain.usecases

import com.tomasm.articles.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesUseCase @Inject constructor(private val repository: ArticlesRepository) {

    operator fun invoke(type: String, period: String, share_type: String) = repository.getArticles(type, period, share_type)

}