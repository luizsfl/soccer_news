package pedroluiz.projeto.soccernews.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractor
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractorImp
import pedroluiz.projeto.soccernews.presentation.ViewState

class NewsViewModel(
    private val newsInteractorImp: NewsInteractor
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun getAllNews() {
        viewModelScope.launch {
            newsInteractorImp.getAllNews()
                .onStart { setLoading(isLoading = true) }
                .catch { setErro(textErro = it.message.orEmpty()) }
                .collect { setList(listNews = it) }
        }
    }

    fun saveNews(news: News) {
        CoroutineScope(Dispatchers.IO).launch {
            newsInteractorImp.saveNews(news)
        }
    }

    fun filterList(text: String) {
        if (text.isEmpty()) {
            getAllNews()
        } else {
            viewModelScope.launch {
                newsInteractorImp.filterNews(text).collect { setList(listNews = it) }
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _viewState.value = ViewState.SetLoading(isLoading = isLoading)
    }

    private fun setErro(textErro: String) {
        _viewState.value = ViewState.LoadFailure(textErro)
    }

    private fun setList(listNews: List<News>) {
        setLoading(false)
        _viewState.value = ViewState.SetNewsListLoaded(listNews)
    }
}