package pedroluiz.projeto.soccernews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pedroluiz.projeto.soccernews.domain.News

@Database(entities = [News::class], version = 1)
abstract class SoccerNewsDb : RoomDatabase() {
    abstract fun newsDao(): NewsDao?
}