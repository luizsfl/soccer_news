package pedroluiz.projeto.soccernews.domain.useCase

import pedroluiz.projeto.soccernews.data.repository.SoccerNewsRepository

class GetAllNewsUseCase(
    private val soccerNewsRepository: SoccerNewsRepository,
) {
    operator fun invoke() = soccerNewsRepository.getAllNews()
}