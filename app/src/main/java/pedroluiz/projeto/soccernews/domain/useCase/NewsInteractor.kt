package pedroluiz.projeto.soccernews.domain.useCase

import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.soccernews.data.model.entity.News

interface NewsInteractor {

    fun filterNews(text:String) : Flow<List<News>>

    fun getAllNewsFavorite(): Flow<List<News>>

    fun getAllNews() :Flow<List<News>>

   suspend fun saveNews(news: News)

}