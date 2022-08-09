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

class FavoriteViewModel(
    private val newsInteractorImp: NewsInteractorImp
) : ViewModel() {

    var _newsAdapter: MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    val newsAdapter: LiveData<List<News>> = _newsAdapter

    fun loadFavoriteNews() {
        viewModelScope.launch {
            newsInteractorImp.getAllNewsFavorite().collect { listNews ->
                _newsAdapter.postValue(listNews)
            }
        }
    }

    fun saveNews(news: News) {
        CoroutineScope(Dispatchers.IO).launch {
            newsInteractorImp.saveNews(news)
        }
    }
}