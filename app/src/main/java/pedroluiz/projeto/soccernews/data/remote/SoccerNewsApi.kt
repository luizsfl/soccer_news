package pedroluiz.projeto.soccernews.data.remote

import pedroluiz.projeto.soccernews.domain.model.News
import retrofit2.Call
import retrofit2.http.GET

interface SoccerNewsApi {
    @GET("news.json")
     fun getListNews(): Call<List<News>>
}
