package pedroluiz.projeto.soccernews.domain.useCase

import pedroluiz.projeto.soccernews.domain.repository.SoccerNewsRepository
import pedroluiz.projeto.soccernews.data.model.entity.News

class SaveNewsUseCase(
    private val soccerNewsRepository: SoccerNewsRepository,
) {
    suspend operator fun invoke(news: News) = soccerNewsRepository.insert(news)
}