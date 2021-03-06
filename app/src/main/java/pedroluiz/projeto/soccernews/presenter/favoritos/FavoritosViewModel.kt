package pedroluiz.projeto.soccernews.presenter.favoritos

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.News

class FavoritosViewModel : ViewModel() {

    private lateinit var news : LiveData<List<News>>

    fun loadFavoritoNews():LiveData<List<News>> {
        return SoccerNewsRepository().instance.localDb.newsDao().loadFavoriteNews(true)
    }

    fun saveNews(news:News){
        AsyncTask.execute(Runnable {
            SoccerNewsRepository().instance.localDb.newsDao().insert(news)
        })
    }
}