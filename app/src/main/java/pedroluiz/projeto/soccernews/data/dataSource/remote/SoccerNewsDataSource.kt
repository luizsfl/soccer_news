package pedroluiz.projeto.soccernews.data.dataSource.remote

import pedroluiz.projeto.soccernews.data.dataSource.ResponseLiveData
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsApi

class SoccerNewsDataSource(
    private val apiService: SoccerNewsApi,
    ) : ResponseLiveData() {

    suspend fun getData()  = getResult{ apiService.getListNews()}

}