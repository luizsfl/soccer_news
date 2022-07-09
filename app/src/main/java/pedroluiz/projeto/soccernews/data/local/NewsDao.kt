package pedroluiz.projeto.soccernews.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pedroluiz.projeto.soccernews.domain.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM News WHERE favorito = :favorite")
    fun loadFavoriteNews(favorite: Boolean?): LiveData<List<News?>?>?

    @Query("SELECT count(*) FROM News WHERE id = :id AND favorito = :favorite")
    fun validFavorito(id: Int?, favorite: Boolean?): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News?)
}