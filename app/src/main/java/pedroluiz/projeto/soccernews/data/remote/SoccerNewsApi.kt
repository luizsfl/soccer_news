package pedroluiz.projeto.soccernews.data.remote

import pedroluiz.projeto.soccernews.domain.model.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface SoccerNewsApi {
    @GET("news.json")
    suspend fun getListNews(): Response<List<News>>
}
