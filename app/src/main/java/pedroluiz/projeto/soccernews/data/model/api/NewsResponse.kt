package pedroluiz.projeto.soccernews.data.model.api


data class NewsResponse(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val link: String,
    var favorite: Boolean
)