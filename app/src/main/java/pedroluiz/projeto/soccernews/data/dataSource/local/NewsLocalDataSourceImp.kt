package pedroluiz.projeto.soccernews.data.dataSource.local

import kotlinx.coroutines.flow.Flow
import org.w3c.dom.Text
import pedroluiz.projeto.soccernews.data.dao.NewsDao
import pedroluiz.projeto.soccernews.data.model.entity.News

class NewsLocalDataSourceImp(private val newsDao: NewsDao): NewsLocalDataSource {

    override fun getLocalNewsList(): Flow<List<News>> = newsDao.AllNews()

    override suspend fun setLocalNews(news: News){
        newsDao.insert(news)
    }

    override fun loadFavoriteNews(favorite: Boolean?):Flow<List<News>>{
        return newsDao.loadFavoriteNews(favorite)
    }

    override fun filterNews(text: String): Flow<List<News>> {
        return newsDao.filterNews(text)
    }

    override suspend fun validFavorite(id: Int?, favorite: Boolean?): Int {
        return newsDao.validFavorite(id,favorite)
    }

}