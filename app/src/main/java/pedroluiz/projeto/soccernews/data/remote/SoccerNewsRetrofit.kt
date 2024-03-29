package pedroluiz.projeto.soccernews.data.remote

import android.content.Context
import androidx.room.Room
import pedroluiz.projeto.soccernews.data.db.SoccerNewsDb
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class SoccerNewsRetrofit(private val context: Context?) {

    private val REMOTE_API_URL = "https://luizsfl.github.io/soccer-news-api/"
    private val LOCAL_DB_NAME = "soccer-news"

    fun getInstanceRetrofit(): SoccerNewsApi {
        return   Retrofit.Builder()
            .baseUrl(REMOTE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SoccerNewsApi::class.java)
    }

    fun getLocalDb(): SoccerNewsDb {
        return  Room.databaseBuilder(context!!, SoccerNewsDb::class.java, LOCAL_DB_NAME)
            .build()
    }
}