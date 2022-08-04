package pedroluiz.projeto.soccernews.data.dataSource.local

import androidx.lifecycle.LiveData
import pedroluiz.projeto.soccernews.domain.model.News

interface NewsLocalDataSource {

    fun getLocalNewsList(): LiveData<List<News>>

    suspend fun setLocalNews(news: News)

    fun loadFavoriteNews(favorite: Boolean?): LiveData<List<News>>

    fun validFavorite(id: Int?, favorite: Boolean?): Int

}