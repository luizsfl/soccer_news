package pedroluiz.projeto.soccernews.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pedroluiz.projeto.soccernews.data.dataSource.local.NewsLocalDataSource
import pedroluiz.projeto.soccernews.data.dataSource.remote.SoccerNewsRemoteDataSourceImp
import pedroluiz.projeto.soccernews.data.mapper.remoteToDomain
import pedroluiz.projeto.soccernews.data.model.api.NewsResponse
import pedroluiz.projeto.soccernews.data.model.entity.News

class SoccerNewsRepository(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteRemoteDataSourceImp: SoccerNewsRemoteDataSourceImp,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

) {
    fun loadFavoriteNews(favorito: Boolean) = newsLocalDataSource.loadFavoriteNews(favorito)

    suspend fun insert(news: News) {
        newsLocalDataSource.setLocalNews(news)
    }

    fun filterNews(text: String): Flow<List<News>> {
        return flow {
            newsLocalDataSource.filterNews(text).collect { newsLocal ->
                emit(newsLocal)
            }
        }
    }

    fun getAllNews(): Flow<List<News>> {
        return flow {
            newsLocalDataSource.getLocalNewsList()
            .collect { newsLocal ->
                if (newsLocal.isEmpty()) {
                    val newsResponse = newsRemoteRemoteDataSourceImp.getListNews()
                    saveLocalData(newsResponse)
                    emit(newsResponse.remoteToDomain())
                } else {
                    emit(newsLocal)
                }
            }
        }.flowOn(dispatcher)
    }

    private suspend fun saveLocalData(usersResponse: List<NewsResponse>) {
        for (news in usersResponse.remoteToDomain()) {
            val favorito = if (newsLocalDataSource.validFavorite(news.id, true) > 0)
                true else false

            news.favorite = favorito
            newsLocalDataSource.setLocalNews(news)
        }
    }
}

