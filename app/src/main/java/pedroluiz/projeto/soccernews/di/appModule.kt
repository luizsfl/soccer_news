package pedroluiz.projeto.soccernews.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.data.dataSource.SoccerNewsDataSource
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsRetrofit
import pedroluiz.projeto.soccernews.domain.useCase.*
import pedroluiz.projeto.soccernews.presenter.favoritos.FavoritosViewModel
import pedroluiz.projeto.soccernews.presenter.news.NewsViewModel

val remoteModule = module {
    single {  SoccerNewsRetrofit(androidContext()).getInstanceRetrofit() }
}
val newsModule = module {

    single { SoccerNewsDataSource(get())}

    single { SoccerNewsRetrofit(androidContext())}
    single { SoccerNewsRepository(soccerNewsRetrofit = get(),get()) }
    single { SaveNewsUseCase(soccerNewsRepository = get())}
    single { ValidFavoritoNewsUseCase(soccerNewsRepository = get())}
    single { FilterNewsUseCase()}
    single { GetAllNewsUseCase(soccerNewsRepository = get())}

    viewModel {
        NewsViewModel(
            soccerNewsRepository = get(),
            saveNewsUseCase = get(),
            validFavoritoNewsUseCase = get(),
            filterNewsUseCase = get(),
            getAllNewsUseCase = get(),
            androidContext()
        )
    }

}

val favoritosModule = module {

    single { SoccerNewsDataSource(get()) }


    factory { SoccerNewsRetrofit(androidContext()) }
    factory { SoccerNewsRepository(soccerNewsRetrofit = get(), get()) }
    factory { GetAllNewsFavoritoUseCase(soccerNewsRepository = get()) }
    factory { SaveNewsUseCase(soccerNewsRepository = get()) }

    viewModel {
        FavoritosViewModel(
            soccerNewsRepository = get(),
            getAllNewsFavoritoUseCase = get(),
            saveNewsUseCase = get()
        )
    }

}


