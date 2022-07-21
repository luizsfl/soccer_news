package pedroluiz.projeto.soccernews.presenter.news

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.domain.useCase.FilterNewsUseCase
import pedroluiz.projeto.soccernews.domain.useCase.GetAllNewsUseCase
import pedroluiz.projeto.soccernews.domain.useCase.SaveNewsUseCase
import pedroluiz.projeto.soccernews.domain.useCase.ValidFavoritoNewsUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsViewModel(
    private val soccerNewsRepository : SoccerNewsRepository,
    private val saveNewsUseCase : SaveNewsUseCase,
    private val validFavoritoNewsUseCase: ValidFavoritoNewsUseCase,
    private val filterNewsUseCase: FilterNewsUseCase,
    private val getAllNewsUseCase: GetAllNewsUseCase,
    context: Context
): ViewModel() {

    private var news: MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    private var newsApi: MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    private var state: MutableLiveData<State> = MutableLiveData()

    var listNews: LiveData<List<News>> = this.news
    val getState: LiveData<State> = this.state

    enum class State {
        DOING, DONE, ERROR
    }

     fun findNews() {

         state.value = State.DOING

         getAllNewsUseCase.invoke().getListNews().enqueue(object : Callback<List<News>> {

             override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                 if (response.isSuccessful) {
                     newsApi.value = response.body()
                     news.value = newsApi.value
                     validFavorito()
                     state.value = State.DONE
                 } else {
                     state.value = State.ERROR
                 }
             }

             override fun onFailure(call: Call<List<News>>, t: Throwable) {
                 state.value = State.ERROR
             }

         })

    }

    fun saveNews(news: News) {
          CoroutineScope(Dispatchers.IO).launch {
              saveNewsUseCase(news)
          }
    }

    fun validFavorito(){
        CoroutineScope(Dispatchers.IO).launch {
            news = validFavoritoNewsUseCase(news)
        }
    }

    fun filterList(text :String){
           news.value =   filterNewsUseCase(newsApi,text).value
   }

}
