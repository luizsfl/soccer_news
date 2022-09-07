package pedroluiz.projeto.soccernews.domain.repository

import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.soccernews.data.model.api.NewsResponse
import pedroluiz.projeto.soccernews.data.model.entity.News

interface SoccerNewsRepository {

        fun loadFavouriteNews(favourite: Boolean): Flow<List<News>>

        suspend fun insert(news: News)

        fun filterNews(text: String) : Flow<List<News>>

        fun getAllNews(): Flow<List<News>>

       suspend fun saveLocalData(usersResponse: List<NewsResponse>)
}