package pedroluiz.projeto.soccernews.presentation.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.domain.useCase.GetAllNewsFavoriteUseCase
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractorImp
import pedroluiz.projeto.soccernews.domain.useCase.SaveNewsUseCase

class FavoritosViewModel(
    private val newsInteractorImp: NewsInteractorImp
    ) : ViewModel() {

    fun loadFavoritoNews():LiveData<List<News>> {
        return newsInteractorImp.getAllNewsFavorite()
    }

    fun saveNews(news: News){
        CoroutineScope(Dispatchers.IO).launch {
            newsInteractorImp.saveNews(news)
        }
    }
}