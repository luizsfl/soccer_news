package pedroluiz.projeto.soccernews.domain.useCase

import androidx.lifecycle.LiveData
import pedroluiz.projeto.soccernews.data.repository.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.utils.Resource

class GetAllNewsUseCase(
    private val soccerNewsRepository: SoccerNewsRepository,
) {
    operator fun invoke(): LiveData<Resource<List<News>>> {
        return soccerNewsRepository.getAllNews()
     }

}