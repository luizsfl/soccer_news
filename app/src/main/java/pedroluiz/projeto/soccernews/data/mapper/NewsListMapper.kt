package pedroluiz.projeto.soccernews.data.mapper

import pedroluiz.projeto.soccernews.data.model.api.NewsResponse
import pedroluiz.projeto.soccernews.data.model.entity.News

fun List<NewsResponse>.remoteToDomain() = map {
    News(
        id = it.id,
        title = it.title,
        description = it.description,
        image = it.image,
        link = it.link,
        favourite = false,
    )
}