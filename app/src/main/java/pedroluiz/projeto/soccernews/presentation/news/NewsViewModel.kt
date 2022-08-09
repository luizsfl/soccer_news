package pedroluiz.projeto.soccernews.presentation.news

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
                .onStart { _viewState.value = ViewState.SetLoading(isLoading = true) }
                .catch{
                    _viewState.value = ViewState.LoadFailure(it.message.orEmpty())
                }
                .collect {
                    _viewState.value = ViewState.SetNewsListLoaded(it)
                    _viewState.value = ViewState.SetLoading(isLoading = false)
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
