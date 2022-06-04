package pedroluiz.projeto.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pedroluiz.projeto.soccernews.domain.News

class NewsViewModel : ViewModel() {



    private val news = MutableLiveData<List<News>>().apply {

        val  news = mutableListOf<News>()
        //TODO Remover mock de rotina
        news.add(News("Titulo","Descricao"))
        news.add(News("Titulo","Descricao"))
        news.add(News("Titulo","Descricao"))
        news.add(News("Titulo","Descricao"))
        news.add(News("Titulo","Descricao"))
        news.add(News("Titulo","Descricao"))

            this.value = news
    }

    val listNews: LiveData<List<News>> = news
}