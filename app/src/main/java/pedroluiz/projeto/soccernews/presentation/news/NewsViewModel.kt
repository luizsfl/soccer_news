package pedroluiz.projeto.soccernews.presentation.news

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractorImp

class NewsViewModel(
    private val newsInteractorImp: NewsInteractorImp
) : ViewModel() {

    var _newsAdapter: MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    val newsAdapter: LiveData<List<News>> = _newsAdapter

    fun getAllNews() {
        CoroutineScope(Dispatchers.IO).launch {
            newsInteractorImp.getAllNews().collect { listNews ->
                _newsAdapter.postValue(listNews)
            }
        }
    }

    fun saveNews(news: News) {
        CoroutineScope(Dispatchers.IO).launch {
            newsInteractorImp.saveNews(news)
        }
    }

    fun filterList(text: String) {
        if (text.isEmpty()) {
            getAllNews()
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                newsInteractorImp.filterNews(text).collect { listNews ->
                    _newsAdapter.postValue(listNews)
                }
            }
        }
    }

}
