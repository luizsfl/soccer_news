package pedroluiz.projeto.soccernews.presenter.favoritos

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.News

class FavoritosViewModel : ViewModel() {

    private lateinit var news : LiveData<List<News>>

    fun loadFavoritoNews():LiveData<List<News>> {
        return SoccerNewsRepository().instance.localDb.newsDao().loadFavoriteNews(true)
    }

    fun saveNews(news:News){
        CoroutineScope(Dispatchers.IO).launch {
            SoccerNewsRepository().instance.localDb.newsDao().insert(news)
        }
    }
}