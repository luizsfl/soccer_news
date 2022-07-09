package pedroluiz.projeto.soccernews.data.remote

import pedroluiz.projeto.soccernews.domain.News
import retrofit2.Call
import retrofit2.http.GET

interface SoccerNewsApi {
    @get:GET("news.json")
    val news: Call<List<News?>?>?
}