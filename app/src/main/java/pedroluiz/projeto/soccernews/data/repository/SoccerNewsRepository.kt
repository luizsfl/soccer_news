package pedroluiz.projeto.soccernews.data.repository


import pedroluiz.projeto.soccernews.data.dataSource.SoccerNewsDataSource
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsRetrofit
import pedroluiz.projeto.soccernews.utils.performGetOperation


class SoccerNewsRepository(
    private val newsLocalDataSource: SoccerNewsRetrofit,
    private val newsRemoteDataSource: SoccerNewsDataSource
){

     fun getLocalDb() = newsLocalDataSource.getLocalDb().newsDao()

     fun getAllNews() = performGetOperation(
          databaseQuery = { newsLocalDataSource.getLocalDb().newsDao().AllNews() },
          networkCall = { newsRemoteDataSource.getData() },
               saveCallResult = { listNews ->
                    for  (news in listNews){
                        val favorito = if(this.getLocalDb().validFavorito(news.id,true) >0)
                            true else false

                        news.favorito = favorito
                        this.getLocalDb().insert(news)
                    }
          })
}

