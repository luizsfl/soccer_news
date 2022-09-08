package pedroluiz.projeto.soccernews.data.dataSource.local

import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.soccernews.data.dao.NewsDao
import pedroluiz.projeto.soccernews.data.model.entity.News

class NewsLocalDataSourceImp(private val newsDao: NewsDao): NewsLocalDataSource {

    override fun getLocalNewsList(): Flow<List<News>> = newsDao.AllNews()

    override suspend fun setLocalNews(news: News) = newsDao.insert(news)

    override fun loadFavouriteNews(favourite: Boolean?):Flow<List<News>>{
        return newsDao.loadFavouriteNews(favourite)
    }

    override fun filterNews(text: String): Flow<List<News>> {
        return newsDao.filterNews(text)
    }

    override suspend fun validFavourite(id: Int?, favourite: Boolean?): Int {
        return newsDao.validFavourite(id,favourite)
    }
}