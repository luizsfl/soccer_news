package pedroluiz.projeto.soccernews.domain.useCase

import androidx.lifecycle.MutableLiveData
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.model.News

class ValidFavoritoNewsUseCase(
   private val soccerNewsRepository: SoccerNewsRepository
) {
    operator fun invoke(news: MutableLiveData<List<News>>):MutableLiveData<List<News>>{

        if (news.value != null) {
            for (i in news.value!!?.indices) {
                news.value?.get(i)?.favorito = if (soccerNewsRepository.getLocalDb()
                        .validFavorito(news.value?.get(i)?.id,true   )>0) true else false
            }
        }

        return news
    }
}