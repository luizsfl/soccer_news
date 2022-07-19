package pedroluiz.projeto.soccernews.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import pedroluiz.projeto.soccernews.domain.model.News;

@Database(entities = {News.class}, version = 1)
public abstract class SoccerNewsDb extends RoomDatabase {

    public abstract NewsDao newsDao();

}

