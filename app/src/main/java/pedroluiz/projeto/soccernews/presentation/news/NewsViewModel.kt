package pedroluiz.projeto.soccernews.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractor
import pedroluiz.projeto.soccernews.presentation.ViewState

class NewsViewModel(
    private val newsInteractor: NewsInteractor
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun getAllNews() {
        viewModelScope.launch {
            newsInteractor.getAllNews()
                .onStart { setLoading(isLoading = true) }
                .catch { setErro(textErro = it.message.orEmpty()) }
                .collect { setList(listNews = it) }
        }
    }

    fun saveNews(news: News) {
        CoroutineScope(Dispatchers.IO).launch {
            newsInteractor.saveNews(news)
        }
    }

    fun filterList(text: String) {
        if (text.isEmpty()) {
            getAllNews()
        } else {
            viewModelScope.launch {
                newsInteractor.filterNews(text).collect { setList(listNews = it) }
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