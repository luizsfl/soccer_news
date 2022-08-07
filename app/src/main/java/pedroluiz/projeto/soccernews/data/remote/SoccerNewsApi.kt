package pedroluiz.projeto.soccernews.data.remote

import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.soccernews.data.model.api.NewsResponse
import pedroluiz.projeto.soccernews.data.model.entity.News
import retrofit2.http.GET

interface SoccerNewsApi {
    @GET("news.json")
    suspend fun getListNews(): List<NewsResponse>
}
