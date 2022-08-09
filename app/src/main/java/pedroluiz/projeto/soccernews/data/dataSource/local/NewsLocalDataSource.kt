package pedroluiz.projeto.soccernews.data.dataSource.local

import kotlinx.coroutines.flow.Flow
import org.w3c.dom.Text
import pedroluiz.projeto.soccernews.data.model.entity.News

interface NewsLocalDataSource {

    fun getLocalNewsList(): Flow<List<News>>

    suspend fun setLocalNews(news: News)

    fun loadFavoriteNews(favorite: Boolean?): Flow<List<News>>

    fun filterNews(text: String): Flow<List<News>>

    suspend fun validFavorite(id: Int?, favorite: Boolean?): Int

}