package pedroluiz.projeto.soccernews.presenter.news

import androidx.lifecycle.*
import kotlinx.coroutines.*
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.domain.useCase.FilterNewsUseCase
import pedroluiz.projeto.soccernews.domain.useCase.GetAllNewsUseCase
import pedroluiz.projeto.soccernews.domain.useCase.SaveNewsUseCase


class NewsViewModel(
    private val saveNewsUseCase : SaveNewsUseCase,
    private val filterNewsUseCase: FilterNewsUseCase,
    private val getAllNewsUseCase: GetAllNewsUseCase
): ViewModel() {

    var newsApi: MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    var newsAltAdapter :MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    var listNewsUseCase = getAllNewsUseCase.invoke()
    val newsAdapter: LiveData<List<News>> = newsAltAdapter

    fun alterNewsAdapter(listNews : List<News>){
        newsAltAdapter.value = listNews
        newsApi.value = listNews
    }

    fun saveNews(news: News) {
          CoroutineScope(Dispatchers.IO).launch {
              saveNewsUseCase(news)
          }
    }

    fun filterList(text :String){
        newsAltAdapter.value =   filterNewsUseCase(newsApi,text).value
   }

}
