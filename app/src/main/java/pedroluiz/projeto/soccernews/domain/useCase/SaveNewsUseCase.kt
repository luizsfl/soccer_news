package pedroluiz.projeto.soccernews.domain.useCase

import pedroluiz.projeto.soccernews.data.repository.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News

class SaveNewsUseCase(
    private val soccerNewsRepository: SoccerNewsRepository,
) {
    suspend operator fun invoke(news: News) {
        soccerNewsRepository.insert(news)
    }
}