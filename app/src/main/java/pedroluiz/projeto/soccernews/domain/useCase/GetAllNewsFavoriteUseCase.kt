package pedroluiz.projeto.soccernews.domain.useCase

import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.data.repository.SoccerNewsRepository

class GetAllNewsFavoriteUseCase(
    private val soccerNewsRepository: SoccerNewsRepository
) {
    operator fun invoke(): Flow<List<News>>  {
        return soccerNewsRepository.loadFavoriteNews(true)
    }
}