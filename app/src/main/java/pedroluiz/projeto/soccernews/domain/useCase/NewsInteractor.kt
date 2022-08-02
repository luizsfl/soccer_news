package pedroluiz.projeto.soccernews.domain.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.utils.Resource

interface NewsInteractor {

    fun filterNews(newsApi: MutableLiveData<List<News>>, text:String) : MutableLiveData<List<News>>

    fun getAllNewsFavorite(): LiveData<List<News>>

    fun getAllNews():LiveData<Resource<List<News>>>

   suspend fun saveNews(news: News)

}