package pedroluiz.projeto.soccernews.presenter.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pedroluiz.projeto.soccernews.data.SoccerNewsRepository
import pedroluiz.projeto.soccernews.presenter.favoritos.FavoritosViewModel
import pedroluiz.projeto.soccernews.presenter.news.NewsViewModel


val newsModule = module {

    factory {
        SoccerNewsRepository(androidContext())
    }

    viewModel {
        NewsViewModel(
            soccerNewsRepository = get()
        )
    }
}


val favoritosModule = module {

    factory {
        SoccerNewsRepository(androidContext())
    }

    viewModel {
        FavoritosViewModel(
            soccerNewsRepository = get()
        )
    }
}