package pedroluiz.projeto.soccernews.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.soccernews.data.model.entity.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM News")
    fun AllNews(): Flow<List<News>>

    @Query("SELECT * FROM News WHERE favorite = :favorite")
        fun loadFavoriteNews(favorite: Boolean?): Flow<List<News>>

    @Query("SELECT count(*) FROM News WHERE id = :id AND favorite = :favorite")
    fun validFavorite(id: Int?, favorite: Boolean?): Int

    @Query("SELECT * FROM News " +
            "WHERE title like '%'|| :text || '%' or description like '%'|| :text || '%'")
    fun filterNews(text: String): Flow<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News?)
}
