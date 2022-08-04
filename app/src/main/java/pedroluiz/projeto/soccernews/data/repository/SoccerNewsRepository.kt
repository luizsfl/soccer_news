package pedroluiz.projeto.soccernews.data.repository


import pedroluiz.projeto.soccernews.data.dataSource.remote.SoccerNewsDataSource
import pedroluiz.projeto.soccernews.data.dataSource.local.NewsLocalDataSource
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.utils.performGetOperation

class SoccerNewsRepository(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: SoccerNewsDataSource
){
    fun loadFavoriteNews(favorito :Boolean) = newsLocalDataSource.loadFavoriteNews(favorito)

    suspend fun insert(news:News){
        newsLocalDataSource.setLocalNews(news)
    }
     fun getAllNews() = performGetOperation(
          databaseQuery = { newsLocalDataSource.getLocalNewsList() },
          networkCall = { newsRemoteDataSource.getData() },
               saveCallResult = { listNews ->
                    for  (news in listNews){
                        val favorito = if(newsLocalDataSource.validFavorite(news.id,true) >0)
                            true else false

                        news.favorite = favorito
                        newsLocalDataSource.setLocalNews(news)
                    }
          })
}

