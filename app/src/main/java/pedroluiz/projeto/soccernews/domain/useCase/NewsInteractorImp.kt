package pedroluiz.projeto.soccernews.domain.useCase

import kotlinx.coroutines.flow.Flow
import pedroluiz.projeto.soccernews.data.model.entity.News

class NewsInteractorImp(
    private val filterNewsUseCase: FilterNewsUseCase,
    private val getAllNewsFavouriteUseCase: GetAllNewsFavouriteUseCase,
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase
) : NewsInteractor {

    override fun getAllNewsFavourite(): Flow<List<News>> = getAllNewsFavouriteUseCase()

    override fun getAllNews() = getAllNewsUseCase()

    override suspend fun saveNews(news: News) = saveNewsUseCase(news)

    override fun filterNews(text: String) = filterNewsUseCase(text)
}
