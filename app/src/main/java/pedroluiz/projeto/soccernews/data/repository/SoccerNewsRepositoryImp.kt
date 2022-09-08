package pedroluiz.projeto.soccernews.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pedroluiz.projeto.soccernews.data.dataSource.local.NewsLocalDataSource
import pedroluiz.projeto.soccernews.data.dataSource.remote.SoccerNewsRemoteDataSource
import pedroluiz.projeto.soccernews.data.mapper.remoteToDomain
import pedroluiz.projeto.soccernews.data.model.api.NewsResponse
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.domain.repository.SoccerNewsRepository

class SoccerNewsRepositoryImp(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val SoccerNewsRemoteDataSource: SoccerNewsRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): SoccerNewsRepository {

    override fun loadFavouriteNews(favourite: Boolean) = newsLocalDataSource.loadFavouriteNews(favourite)

    override suspend fun insert(news: News) = newsLocalDataSource.setLocalNews(news)

    override fun filterNews(text: String): Flow<List<News>> {
        return flow {
            newsLocalDataSource.filterNews(text).collect { newsLocal ->
                emit(newsLocal)
            }
        }
    }

    override fun getAllNews(): Flow<List<News>> {
        return flow {
            val newsResponse = SoccerNewsRemoteDataSource.getListNews()
            saveLocalData(newsResponse)

            newsLocalDataSource.getLocalNewsList()
            .collect { newsLocal ->
                if (newsLocal.isEmpty()) {
                    emit(newsResponse.remoteToDomain())
                } else {
                    emit(newsLocal)
                }
            }
        }.flowOn(dispatcher)
    }

    override suspend fun saveLocalData(usersResponse: List<NewsResponse>) {
        for (news in usersResponse.remoteToDomain()) {
            val favourite = if (newsLocalDataSource.validFavourite(news.id, true) > 0)
                true else false

            news.favourite = favourite
            newsLocalDataSource.setLocalNews(news)
        }
    }
}

