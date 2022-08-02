package pedroluiz.projeto.soccernews.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.soccernews.domain.model.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM News")
    fun AllNews(): LiveData<List<News>>

    @Query("SELECT * FROM News WHERE favorito = :favorite")
    fun loadFavoriteNews(favorite: Boolean?): LiveData<List<News>>

    @Query("SELECT count(*) FROM News WHERE id = :id AND favorito = :favorite")
    fun validFavorito(id: Int?, favorite: Boolean?): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News?)
}
