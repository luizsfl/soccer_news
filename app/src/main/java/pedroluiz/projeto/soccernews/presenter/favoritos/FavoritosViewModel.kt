package pedroluiz.projeto.soccernews.presenter.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.domain.useCase.GetAllNewsFavoritoUseCase
import pedroluiz.projeto.soccernews.domain.useCase.SaveNewsUseCase

class FavoritosViewModel(
    private val getAllNewsFavoritoUseCase : GetAllNewsFavoritoUseCase,
    private val saveNewsUseCase: SaveNewsUseCase

    ) : ViewModel() {

    fun loadFavoritoNews():LiveData<List<News>> {
        return getAllNewsFavoritoUseCase()
    }

    fun saveNews(news: News){
        CoroutineScope(Dispatchers.IO).launch {
            saveNewsUseCase(news)
        }
    }
}