package pedroluiz.projeto.soccernews.presenter

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pedroluiz.projeto.soccernews.di.favoritosModule
import pedroluiz.projeto.soccernews.di.newsModule
import pedroluiz.projeto.soccernews.di.remoteModule

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApp)

            modules(newsModule,remoteModule,favoritosModule)

        }
    }

}