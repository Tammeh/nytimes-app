package com.tomasm.articles.data.datasource

import com.tomasm.articles.data.local.ArticlesLocal
import com.tomasm.articles.data.models.entity.ArticlesEntity
import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.articles.data.service.ArticlesService
import com.tomasm.core.functional.Success
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class ArticlesDataSourceImplTest{

    @RelaxedMockK
    private lateinit var service: ArticlesService
    @RelaxedMockK
    private lateinit var local: ArticlesLocal

    lateinit var dataSource : ArticlesDataSourceImpl

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        dataSource = ArticlesDataSourceImpl(service, local)
    }

    @Test
    fun `should get articles on success`() = runBlocking {

        val articles = ArticlesEntity.emptyWithResult()
        coEvery { local.getArticles() } returns articles

        val flow = dataSource.getArticles("mostviewed", "7", "")

        flow.collect { result ->
            result.`should be instance of`<Success<ArticlesView>>()
            when (result) {
                is Success<ArticlesView> -> {
                    result.data shouldBeEqualTo articles.toArticles().toArticlesView()
                }
            }
        }
    }
}