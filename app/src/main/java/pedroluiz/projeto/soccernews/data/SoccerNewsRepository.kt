package pedroluiz.projeto.soccernews.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pedroluiz.projeto.soccernews.data.local.SoccerNewsDb
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsApi
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsRetrofit
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.presenter.news.NewsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoccerNewsRepository(private val soccerNewsRetrofit: SoccerNewsRetrofit){

     fun getListNews() = soccerNewsRetrofit.getInstanceRetrofit()

     fun getLocalDb() = soccerNewsRetrofit.getLocalDb()

}