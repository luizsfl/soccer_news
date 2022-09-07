package pedroluiz.projeto.soccernews.domain.useCase

import pedroluiz.projeto.soccernews.domain.repository.SoccerNewsRepository

class FilterNewsUseCase(
    private val soccerNewsRepository: SoccerNewsRepository
) {
    operator fun invoke(text: String) = soccerNewsRepository.filterNews(text)
}