package pedroluiz.projeto.soccernews.domain.useCase

import pedroluiz.projeto.soccernews.data.repository.SoccerNewsRepository

class FilterNewsUseCase(
    private val soccerNewsRepository: SoccerNewsRepository
) {
    operator fun invoke(text: String) = soccerNewsRepository.filterNews(text)
}