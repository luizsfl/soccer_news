package pedroluiz.projeto.soccernews.data.dataSource.remote

import pedroluiz.projeto.soccernews.data.model.api.NewsResponse

interface SoccerNewsRemoteDataSource {
    suspend fun getListNews(): List<NewsResponse>
}