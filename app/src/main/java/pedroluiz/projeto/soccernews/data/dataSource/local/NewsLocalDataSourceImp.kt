package pedroluiz.projeto.soccernews.data.dataSource.local

import androidx.lifecycle.LiveData
import pedroluiz.projeto.soccernews.data.dao.NewsDao
import pedroluiz.projeto.soccernews.domain.model.News

class NewsLocalDataSourceImp(private val newsDao: NewsDao): NewsLocalDataSource {

    override fun getLocalNewsList(): LiveData<List<News>>  = newsDao.AllNews()

    override suspend fun setLocalNews(news: News) {
        newsDao.insert(news)
    }

    override fun loadFavoriteNews(favorite: Boolean?):  LiveData<List<News>>  = newsDao.loadFavoriteNews(favorite)

    override fun validFavorite(id: Int?, favorite: Boolean?): Int = newsDao.validFavorite(id,favorite)

}