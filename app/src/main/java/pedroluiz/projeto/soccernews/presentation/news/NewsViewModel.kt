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
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractorImp
import pedroluiz.projeto.soccernews.presentation.ViewState

class NewsViewModel(
    private val newsInteractorImp: NewsInteractorImp
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun getAllNews() {
        viewModelScope.launch {
            newsInteractorImp.getAllNews()
                .onStart {
                    _viewState.value = ViewState.SetLoading(isLoading = true)
                }
                .catch {
                    _viewState.value = ViewState.LoadFailure(it.message.orEmpty())
                }
                .collect {
                    _viewState.value = ViewState.SetLoading(isLoading = false)
                    _viewState.value = ViewState.SetNewsListLoaded(it)
                }
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
                newsInteractorImp.filterNews(text).collect { listNews ->
                    _viewState.value = ViewState.SetNewsListLoaded(listNews)
                }
            }
        }
    }

}
