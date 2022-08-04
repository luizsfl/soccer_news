package pedroluiz.projeto.soccernews.presentation.news

import androidx.lifecycle.*
import kotlinx.coroutines.*
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.domain.useCase.FilterNewsUseCase
import pedroluiz.projeto.soccernews.domain.useCase.GetAllNewsUseCase
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractorImp
import pedroluiz.projeto.soccernews.domain.useCase.SaveNewsUseCase


class NewsViewModel(
    private val newsInteractorImp: NewsInteractorImp
): ViewModel() {

    var newsApi: MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    var newsAltAdapter :MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    var listNewsUseCase = newsInteractorImp.getAllNews()
    val newsAdapter: LiveData<List<News>> = newsAltAdapter

    fun alterNewsAdapter(listNews : List<News>){
        newsAltAdapter.value = listNews
        newsApi.value = listNews
    }

    fun updateNewsAdapterDb(){
        listNewsUseCase = newsInteractorImp.getAllNews()
    }

    fun saveNews(news: News) {
          CoroutineScope(Dispatchers.IO).launch {
              newsInteractorImp.saveNews(news)
          }
    }

    fun filterList(text :String){
        newsAltAdapter.value = newsInteractorImp.filterNews(newsApi,text).value
   }

}
