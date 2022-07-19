package pedroluiz.projeto.soccernews.presenter.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News

class FavoritosViewModel(
    private val soccerNewsRepository: SoccerNewsRepository
) : ViewModel() {

    private lateinit var news : LiveData<List<News>>

    fun loadFavoritoNews():LiveData<List<News>> {
        return soccerNewsRepository.localDb.newsDao().loadFavoriteNews(true)
    }

    fun saveNews(news: News){
        CoroutineScope(Dispatchers.IO).launch {
            soccerNewsRepository.localDb.newsDao().insert(news)
        }
    }
}