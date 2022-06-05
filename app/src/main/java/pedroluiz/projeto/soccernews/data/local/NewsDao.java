package pedroluiz.projeto.soccernews.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import pedroluiz.projeto.soccernews.domain.News;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM News WHERE favorito = :favorite")
    LiveData<List<News>> loadFavoriteNews(Boolean favorite);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);
}
