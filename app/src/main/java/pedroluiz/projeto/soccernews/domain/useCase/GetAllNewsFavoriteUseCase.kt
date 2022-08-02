package pedroluiz.projeto.soccernews.domain.useCase

import androidx.lifecycle.LiveData
import pedroluiz.projeto.soccernews.data.repository.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News

class GetAllNewsFavoriteUseCase(
    private val soccerNewsRepository: SoccerNewsRepository
) {
    operator fun invoke(): LiveData<List<News>>  {
        return soccerNewsRepository.loadFavoriteNews(true)
    }
}