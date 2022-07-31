package pedroluiz.projeto.soccernews.domain.useCase

import androidx.lifecycle.LiveData
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.utils.Resource

class GetAllNewsUseCase(
    private val soccerNewsRepository: SoccerNewsRepository,
) {
    fun invoke(): LiveData<Resource<List<News>>> {
        return soccerNewsRepository.getAllNews()
     }
}