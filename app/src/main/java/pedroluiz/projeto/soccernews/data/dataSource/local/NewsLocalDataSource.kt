package pedroluiz.projeto.soccernews.data.dataSource.local

import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.soccernews.data.model.entity.News

interface NewsLocalDataSource {

    fun getLocalNewsList(): Flow<List<News>>

    suspend fun setLocalNews(news: News)

    fun loadFavouriteNews(favourite: Boolean?): Flow<List<News>>

    fun filterNews(text: String): Flow<List<News>>

    suspend fun validFavourite(id: Int?, favourite: Boolean?): Int
}