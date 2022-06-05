package pedroluiz.projeto.soccernews.data.local;

import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.RoomDatabase;

import pedroluiz.projeto.soccernews.domain.News;

@Database(entities = {News.class}, version = 1)
public abstract class SoccerNewsDb extends RoomDatabase {

    public abstract NewsDao newsDao();

}

