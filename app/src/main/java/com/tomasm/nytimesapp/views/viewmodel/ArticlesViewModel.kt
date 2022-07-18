package com.tomasm.nytimesapp.views.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.articles.domain.usecases.ArticlesUseCase
import com.tomasm.core.exception.Failure
import com.tomasm.core.functional.Error
import com.tomasm.core.functional.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(private val articlesUseCase: ArticlesUseCase) :
    ViewModel() {

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure get() = _failure

    private val _showSpinner: MutableLiveData<Boolean> = MutableLiveData()
    val showSpinner get() = _showSpinner


    private var _articles = MutableLiveData<ArticlesView>()
    val articles get() = _articles


    fun getArticles(type: String, period: String, share_type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            articlesUseCase(type, period, share_type)
                .onStart { _showSpinner.postValue(true)}
                .onCompletion { _showSpinner.postValue(false)}
                .catch { _failure.postValue(Failure.Throwable(it)) }
                .collect { result ->
                    when (result) {
                        is Success<ArticlesView> -> _articles.postValue(result.data)
                        is Error -> _failure.postValue(result.failure)
                    }

                }
        }
    }

}