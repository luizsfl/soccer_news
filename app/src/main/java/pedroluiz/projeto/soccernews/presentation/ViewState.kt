package pedroluiz.projeto.soccernews.presentation

import pedroluiz.projeto.soccernews.data.model.entity.News

sealed class ViewState {
    data class SetLoading(val isLoading: Boolean) : ViewState()
    data class SetNewsListLoaded(val listNews: List<News> = emptyList()) : ViewState()
    data class LoadFailure(val messageError: String = String()) : ViewState()
}