package com.tomasm.articles.domain.usecases

import com.tomasm.articles.data.models.entity.ArticlesEntity
import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.articles.domain.repository.ArticlesRepository
import com.tomasm.core.functional.Success
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ArticlesUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: ArticlesRepository

    lateinit var getArticlesUseCase: ArticlesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getArticlesUseCase = ArticlesUseCase(repository)
    }

    @Test
    fun `api return articles on success`() = runBlocking {
        val articles = ArticlesEntity.empty()
        every { repository.getArticles("mostviewed", "7", "") } returns flow {
            emit(
                Success(
                    articles.toArticles().toArticlesView()
                )
            )
        }
        val flow = repository.getArticles("mostviewed", "7", "")

        flow.collect{ result ->
            result.`should be instance of`<Success<ArticlesView>>()
            when (result) {
                is Success<ArticlesView> -> {
                    result.data shouldBeEqualTo articles.toArticles().toArticlesView()
                }
            }
        }
    }
}