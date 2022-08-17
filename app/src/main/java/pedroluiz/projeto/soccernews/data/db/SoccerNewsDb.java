package pedroluiz.projeto.soccernews.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import pedroluiz.projeto.soccernews.data.dao.NewsDao;
import pedroluiz.projeto.soccernews.data.model.entity.News;

@Database(entities = {News.class}, version = 1)
public abstract class SoccerNewsDb extends RoomDatabase {

    public abstract NewsDao newsDao();
}

