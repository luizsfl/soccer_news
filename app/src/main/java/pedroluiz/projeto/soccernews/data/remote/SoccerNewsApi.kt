package pedroluiz.projeto.soccernews.data.remote

import pedroluiz.projeto.soccernews.data.model.api.NewsResponse
import retrofit2.http.GET

interface SoccerNewsApi {
    @GET("news.json")
    suspend fun getListNews(): List<NewsResponse>
}
