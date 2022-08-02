package pedroluiz.projeto.soccernews.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import pedroluiz.projeto.soccernews.domain.model.News;
@Dao
public interface NewsDao {

    @Query("SELECT * FROM News")
    LiveData<List<News>> AllNews();

    @Query("SELECT * FROM News WHERE favorito = :favorite")
    LiveData<List<News>> loadFavoriteNews(Boolean favorite);

    @Query("SELECT count(*) FROM News WHERE id = :id AND favorito = :favorite")
    int validFavorito(Integer id,Boolean favorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);
}
