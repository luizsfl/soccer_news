package pedroluiz.projeto.soccernews.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pedroluiz.projeto.soccernews.data.repository.SoccerNewsRepositoryImp
import pedroluiz.projeto.soccernews.data.dataSource.remote.SoccerNewsRemoteDataSourceImp
import pedroluiz.projeto.soccernews.data.dataSource.local.NewsLocalDataSource
import pedroluiz.projeto.soccernews.data.dataSource.local.NewsLocalDataSourceImp
import pedroluiz.projeto.soccernews.data.dataSource.remote.SoccerNewsRemoteDataSource
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsRetrofit
import pedroluiz.projeto.soccernews.domain.repository.SoccerNewsRepository
import pedroluiz.projeto.soccernews.domain.useCase.*
import pedroluiz.projeto.soccernews.presentation.favourite.FavouriteViewModel
import pedroluiz.projeto.soccernews.presentation.news.NewsViewModel

val remoteModule = module {
    factory {  SoccerNewsRetrofit(androidContext()).getInstanceRetrofit() }
}

val localModule = module {
    factory {  SoccerNewsRetrofit(androidContext()).getLocalDb().newsDao() }
}

val newsModule = module {

    factory { SaveNewsUseCase(soccerNewsRepository = get())}
    factory { FilterNewsUseCase(soccerNewsRepository = get())}
    factory { GetAllNewsUseCase(soccerNewsRepository = get())}
    factory<SoccerNewsRemoteDataSource> { SoccerNewsRemoteDataSourceImp(get()) }
    factory<NewsLocalDataSource> { NewsLocalDataSourceImp(newsDao = get())}
    factory<NewsInteractor> { NewsInteractorImp( get(), get(),get(),get() )}
    factory<SoccerNewsRepository> { SoccerNewsRepositoryImp(newsLocalDataSource = get(), SoccerNewsRemoteDataSource = get() ) }

    factory { NewsInteractorImp(
        filterNewsUseCase = get(),
        getAllNewsFavouriteUseCase = get(),
        getAllNewsUseCase = get(),
        saveNewsUseCase = get()
    )}

    viewModel {
        NewsViewModel(
            newsInteractor = get()
        )
    }
}

val favouriteModule = module {

    factory { SoccerNewsRetrofit(androidContext()) }
    factory { GetAllNewsFavouriteUseCase(soccerNewsRepository = get()) }
    factory { SaveNewsUseCase(soccerNewsRepository = get()) }
    factory<SoccerNewsRemoteDataSource> { SoccerNewsRemoteDataSourceImp(get()) }
    factory<SoccerNewsRepository> { SoccerNewsRepositoryImp(newsLocalDataSource = get(), SoccerNewsRemoteDataSource = get() ) }
    factory<NewsInteractor> { NewsInteractorImp( get(), get(),get(),get() )}

    viewModel {
        FavouriteViewModel(
            newsInteractor = get()
        )
    }
}