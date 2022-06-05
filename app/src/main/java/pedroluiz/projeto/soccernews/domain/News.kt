package pedroluiz.projeto.soccernews.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News (
    @PrimaryKey
    val id :Int,
    val title:String,
    val description:String,
    val image: String,
    val link:String,
    var favorito : Boolean
)
