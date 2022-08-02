package pedroluiz.projeto.soccernews.domain.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pedroluiz.projeto.soccernews.domain.model.News
import pedroluiz.projeto.soccernews.utils.Resource

class NewsInteractorImp(
    private val filterNewsUseCase: FilterNewsUseCase,
    private val getAllNewsFavoriteUseCase: GetAllNewsFavoriteUseCase,
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase
):NewsInteractor {

    override fun getAllNewsFavorite(): LiveData<List<News>> = getAllNewsFavoriteUseCase()

    override fun getAllNews(): LiveData<Resource<List<News>>> = getAllNewsUseCase()

    override suspend fun saveNews(news: News) = saveNewsUseCase(news)

    override fun filterNews(
        listNews: MutableLiveData<List<News>>,
        text: String
    ): MutableLiveData<List<News>> = filterNewsUseCase(listNews,text)

}