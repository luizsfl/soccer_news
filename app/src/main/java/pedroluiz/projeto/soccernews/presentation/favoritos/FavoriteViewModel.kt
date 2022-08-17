package pedroluiz.projeto.soccernews.presentation.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractor
import pedroluiz.projeto.soccernews.presentation.ViewState

class FavoriteViewModel(
    private val newsInteractor: NewsInteractor
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun saveNews(news: News) {
        CoroutineScope(Dispatchers.IO).launch {
            newsInteractor.saveNews(news)

            withContext(Dispatchers.Main) {
                loadFavoriteNews()
            }
        }
    }

    fun loadFavoriteNews() {
        viewModelScope.launch {
            newsInteractor.getAllNewsFavorite().collect { listNews ->
                _viewState.value = ViewState.SetNewsListLoaded(listNews)
            }
        }
    }
}