package pedroluiz.projeto.soccernews.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pedroluiz.projeto.soccernews.data.repository.SoccerNewsRepository
import pedroluiz.projeto.soccernews.data.dataSource.SoccerNewsDataSource
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsRetrofit
import pedroluiz.projeto.soccernews.domain.useCase.*
import pedroluiz.projeto.soccernews.presenter.favoritos.FavoritosViewModel
import pedroluiz.projeto.soccernews.presenter.news.NewsViewModel

val remoteModule = module {
    factory {  SoccerNewsRetrofit(androidContext()).getInstanceRetrofit() }
}
val newsModule = module {

    factory { SoccerNewsDataSource(get())}

    factory { SoccerNewsRetrofit(androidContext())}
    factory { SoccerNewsRepository(newsLocalDataSource = get(),get()) }
    factory { SaveNewsUseCase(soccerNewsRepository = get())}
    factory { FilterNewsUseCase()}
    factory { GetAllNewsUseCase(soccerNewsRepository = get())}

    viewModel {
        NewsViewModel(
            saveNewsUseCase = get(),
            filterNewsUseCase = get(),
            getAllNewsUseCase = get())
    }

}

val favoritosModule = module {

    factory { SoccerNewsDataSource(get()) }
    factory { SoccerNewsRetrofit(androidContext()) }
    factory { SoccerNewsRepository(newsLocalDataSource = get(), get()) }
    factory { GetAllNewsFavoritoUseCase(soccerNewsRepository = get()) }
    factory { SaveNewsUseCase(soccerNewsRepository = get()) }

    viewModel {
        FavoritosViewModel(
            getAllNewsFavoritoUseCase = get(),
            saveNewsUseCase = get()
        )
    }

}


