package pedroluiz.projeto.soccernews.domain.useCase

import androidx.lifecycle.LiveData
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News

class GetAllNewsFavoritoUseCase(
    private val soccerNewsRepository: SoccerNewsRepository
) {
    operator fun invoke(): LiveData<List<News>>  {

        return soccerNewsRepository.getLocalDb().loadFavoriteNews(true)
       // return soccerNewsRepository.movies
    }
}