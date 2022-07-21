package pedroluiz.projeto.soccernews.domain.useCase

import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News

class SaveNewsUseCase(
    private val soccerNewsRepository: SoccerNewsRepository,
) {
    operator fun invoke(news: News) {
        soccerNewsRepository.getLocalDb().newsDao().insert(news)
    }
}