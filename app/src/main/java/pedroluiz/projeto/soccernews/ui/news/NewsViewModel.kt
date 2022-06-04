package pedroluiz.projeto.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsApi
import pedroluiz.projeto.soccernews.domain.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsViewModel : ViewModel() {

    private lateinit var service : SoccerNewsApi
    private var news : MutableLiveData<List<News>> = MutableLiveData<List<News>>()

    fun NewsViewModel(){
        var retrofit = Retrofit.Builder()
            .baseUrl("https://luizsfl.github.io/soccer-news-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(SoccerNewsApi::class.java)
        this.findNews()
    }

fun findNews(){
    service.news.enqueue(object : Callback<List<News>> {

        override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
            if (response.isSuccessful){
                news.value = response.body()

            }else{
                //TODO showErrorMessage()

            }

        }
        override fun onFailure(call: Call<List<News>>, t: Throwable) {
            //TODO showErrorMessage()
            // TODO binding.srfMatchs.isRefreshing = false

        }

    } )
}
    val listNews: LiveData<List<News>> = this.news
}
