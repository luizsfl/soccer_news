package pedroluiz.projeto.soccernews.data.dataSource.remote

import pedroluiz.projeto.soccernews.data.remote.SoccerNewsApi

class SoccerNewsRemoteDataSourceImp(
    private val apiService: SoccerNewsApi,
):SoccerNewsRemoteDataSource {

    override suspend fun getListNews() = apiService.getListNews()
}