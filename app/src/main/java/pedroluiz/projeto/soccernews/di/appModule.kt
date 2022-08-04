package pedroluiz.projeto.soccernews.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pedroluiz.projeto.soccernews.data.repository.SoccerNewsRepository
import pedroluiz.projeto.soccernews.data.dataSource.remote.SoccerNewsDataSource
import pedroluiz.projeto.soccernews.data.dataSource.local.NewsLocalDataSource
import pedroluiz.projeto.soccernews.data.dataSource.local.NewsLocalDataSourceImp
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsRetrofit
import pedroluiz.projeto.soccernews.domain.useCase.*
import pedroluiz.projeto.soccernews.presentation.favoritos.FavoriteViewModel
import pedroluiz.projeto.soccernews.presentation.news.NewsViewModel

val remoteModule = module {
    factory {  SoccerNewsRetrofit(androidContext()).getInstanceRetrofit() }
}

val localModule = module {
    factory {  SoccerNewsRetrofit(androidContext()).getLocalDb().newsDao() }
}

val newsModule = module {

    factory { SoccerNewsDataSource(get()) }
    factory { SoccerNewsRepository(newsLocalDataSource = get(), newsRemoteDataSource = get()) }
    factory { SaveNewsUseCase(soccerNewsRepository = get())}
    factory { FilterNewsUseCase()}
    factory { GetAllNewsUseCase(soccerNewsRepository = get())}
    factory<NewsLocalDataSource> { NewsLocalDataSourceImp(newsDao = get())}

    factory { NewsInteractorImp(
        filterNewsUseCase = get(),
        getAllNewsFavoriteUseCase = get(),
        getAllNewsUseCase = get(),
        saveNewsUseCase = get()
    )}

    viewModel {
        NewsViewModel(
            newsInteractorImp = get()
        )
    }
}

val favoritosModule = module {

    factory { SoccerNewsDataSource(get()) }
    factory { SoccerNewsRetrofit(androidContext()) }
    factory { SoccerNewsRepository(newsLocalDataSource = get(), get()) }
    factory { GetAllNewsFavoriteUseCase(soccerNewsRepository = get()) }
    factory { SaveNewsUseCase(soccerNewsRepository = get()) }

    viewModel {
        FavoriteViewModel(
            newsInteractorImp = get()
        )
    }
}


