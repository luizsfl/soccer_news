package pedroluiz.projeto.soccernews.domain.useCase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsApi
import pedroluiz.projeto.soccernews.domain.model.News

class GetAllNewsUseCase(
    private val soccerNewsRepository: SoccerNewsRepository,
) {
    operator fun invoke(): SoccerNewsApi {
        return soccerNewsRepository.getListNews()
     }
}