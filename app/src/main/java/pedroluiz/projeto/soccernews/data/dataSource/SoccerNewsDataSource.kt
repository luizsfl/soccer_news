package pedroluiz.projeto.soccernews.data.dataSource

import pedroluiz.projeto.soccernews.data.remote.SoccerNewsApi


class SoccerNewsDataSource(
    private val apiService: SoccerNewsApi,
    ) : ResponseLiveData() {

    suspend fun getData()  = getResult{ apiService.getListNews()}

}