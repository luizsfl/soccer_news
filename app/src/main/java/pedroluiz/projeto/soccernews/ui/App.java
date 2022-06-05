package pedroluiz.projeto.soccernews.ui;

import android.app.Application;

//Estudar sobre injeção de dependencia dagger ou hilt
//e um anti-pattern utilizado para entendermo melhor a utilização do viewmodel
public class App extends Application {

    private static App instance;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
