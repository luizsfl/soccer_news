package pedroluiz.projeto.soccernews.data


import pedroluiz.projeto.soccernews.data.dataSource.SoccerNewsDataSource
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsRetrofit
import pedroluiz.projeto.soccernews.utils.performGetOperation


class SoccerNewsRepository(
     private val soccerNewsRetrofit: SoccerNewsRetrofit,
     private val newsRemoteDataSource: SoccerNewsDataSource
){

     fun getListNews() = soccerNewsRetrofit.getInstanceRetrofit()

     fun getLocalDb() = soccerNewsRetrofit.getLocalDb().newsDao()



     fun getAllNews() = performGetOperation(
          databaseQuery = { soccerNewsRetrofit.getLocalDb().newsDao().loadFavoriteNews(true) },
          networkCall = { newsRemoteDataSource.getData() },
          saveCallResult = { soccerNewsRetrofit.getLocalDb().newsDao().insert(it.get(0)) })

}

