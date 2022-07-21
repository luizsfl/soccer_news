package pedroluiz.projeto.soccernews.presenter.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.domain.useCase.GetAllNewsFavoritoUseCase
import pedroluiz.projeto.soccernews.domain.useCase.SaveNewsUseCase

class FavoritosViewModel(
    private val soccerNewsRepository: SoccerNewsRepository,
    private val getAllNewsFavoritoUseCase : GetAllNewsFavoritoUseCase,
    private val saveNewsUseCase: SaveNewsUseCase

    ) : ViewModel() {

    private lateinit var news : LiveData<List<News>>

    fun loadFavoritoNews():LiveData<List<News>> {
        //return soccerNewsRepository.getLocalDb().newsDao().loadFavoriteNews(true)
        return getAllNewsFavoritoUseCase()
    }

    fun saveNews(news: News){
        CoroutineScope(Dispatchers.IO).launch {
            soccerNewsRepository.getLocalDb().newsDao().insert(news)
            saveNewsUseCase(news)
        }
    }
}