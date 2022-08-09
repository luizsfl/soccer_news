package pedroluiz.projeto.soccernews.presentation.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractorImp
import pedroluiz.projeto.soccernews.presentation.ViewState

class FavoriteViewModel(
    private val newsInteractorImp: NewsInteractorImp
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun loadFavoriteNews() {
        viewModelScope.launch {
            newsInteractorImp.getAllNewsFavorite().collect { listNews ->
                _viewState.value = ViewState.SetNewsListLoaded(listNews)
            }
        }
    }

    fun saveNews(news: News) {
        CoroutineScope(Dispatchers.IO).launch {
            newsInteractorImp.saveNews(news)
        }
    }
}